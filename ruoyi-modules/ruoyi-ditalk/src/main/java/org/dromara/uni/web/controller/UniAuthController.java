package org.dromara.uni.web.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.system.domain.vo.SysClientVo;
import org.dromara.system.service.ISysClientService;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.uni.web.domain.bo.UniLoginBo;
import org.dromara.uni.web.domain.bo.UniRegisterBo;
import org.dromara.uni.web.domain.vo.UniLoginVo;
import org.dromara.uni.web.service.IAuthStrategy;
import org.dromara.uni.web.service.IUniLoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证
 *
 * @author weidixian
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/uni")
public class UniAuthController {


    private final IUniLoginService uniLoginService;
    private final ISysClientService clientService;

    @Resource
    private WxMaService wxMaService;
    @Resource
    private LockTemplate lockTemplate;

    /**
     * 微信小程序注册及登录接口
     */
    @RepeatSubmit
    @PostMapping("/weixin/login")
    @SaIgnore
    public R<UniLoginVo> weixinLogin(@Validated @RequestBody UniLoginBo loginBo) {
        loginBo.setPlatform("mp-weixin"); // 微信小程序
        try {
            // 校验租户
            uniLoginService.checkTenant(loginBo.getTenantId());
            TenantHelper.setDynamic(loginBo.getTenantId());
            String appId = loginBo.getAppId();
            String code = loginBo.getCode();
            if (!wxMaService.switchover(appId)) {
                throw new IllegalArgumentException(String.format("未找到对应appId=[%s]的配置，请核实！", appId));
            }
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            String openId = session.getOpenid();
            final LockInfo lockInfo = lockTemplate.lock(openId, 10000L, 3000L);
            try {
                if (null == lockInfo) {
                    throw new RuntimeException("业务繁忙中,请稍后再试");
                }
                // 1 注册
                loginBo.setOpenId(openId);
                UniRegisterBo registerBo = BeanUtil.copyProperties(loginBo, UniRegisterBo.class);
                uniLoginService.uniRegister(registerBo);
                // 2 登录
                UniLoginVo loginVo = this.doLogin(loginBo);
                return R.ok(loginVo);
            } finally {
                lockTemplate.releaseLock(lockInfo);
            }
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.toString());
        } finally {
            WxMaConfigHolder.remove(); //清理ThreadLocal
            TenantHelper.clearDynamic();
        }
    }

    private UniLoginVo doLogin(UniLoginBo uniLoginBo) {
        // 授权类型和客户端id
        String clientId = uniLoginBo.getClientId(); // sys_client：client_id
        String grantType = uniLoginBo.getGrantType(); // sys_client：grant_type
        SysClientVo client = clientService.queryByClientId(clientId);
        String body = JsonUtils.toJsonString(uniLoginBo); // 框架接口定义需要 JSON
        return IAuthStrategy.login(body, client, grantType);
    }

    /**
     * 验证客户 Token 是否有效的接口
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/verify")
    public R verify() {
        return R.ok();
    }

}
