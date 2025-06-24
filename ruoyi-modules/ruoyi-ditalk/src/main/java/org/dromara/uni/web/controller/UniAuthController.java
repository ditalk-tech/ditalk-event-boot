package org.dromara.uni.web.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.R;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.system.domain.vo.SysClientVo;
import org.dromara.system.service.ISysClientService;
import org.dromara.uni.web.domain.bo.UniLoginBo;
import org.dromara.uni.web.domain.vo.UniLoginVo;
import org.dromara.uni.web.service.IAuthStrategy;
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

    private final ISysClientService clientService;

    /**
     * 微信小程序注册及登录接口
     */
    @RepeatSubmit
    @PostMapping("/weixin/login")
    @SaIgnore
    public R<UniLoginVo> weixinLogin(@Validated @RequestBody UniLoginBo loginBo) {
        loginBo.setPlatform("weixin"); // UniApp的provider平台标识
        UniLoginVo loginVo = this.doLogin(loginBo);
        return R.ok(loginVo);
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

    /**
     * My id
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/getMyId")
    public R getMyId() {
        return R.ok(LoginHelper.getUserId());
    }

}
