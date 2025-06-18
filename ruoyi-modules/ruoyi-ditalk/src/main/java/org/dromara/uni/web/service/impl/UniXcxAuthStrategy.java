package org.dromara.uni.web.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWechatMiniProgramRequest;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.enums.UserStatus;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.mybatis.enums.DataScopeType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.module.config.service.IUniAuthConfigService;
import org.dromara.system.domain.vo.SysClientVo;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.uni.web.domain.bo.UniLoginBody;
import org.dromara.uni.web.domain.bo.UniLoginUser;
import org.dromara.uni.web.domain.bo.UniRegisterBo;
import org.dromara.uni.web.domain.vo.UniLoginVo;
import org.dromara.uni.web.service.IAuthStrategy;
import org.dromara.uni.web.service.IUniLoginService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * customer通过小程序认证策略
 *
 * @author weidixian
 */
@Slf4j
@Service("uniXcx" + IAuthStrategy.BASE_NAME)
public class UniXcxAuthStrategy implements IAuthStrategy {

    @Resource
    private IUniLoginService uniLoginService;
    @Resource
    private IUniAuthConfigService uniAuthConfigService;
    @Resource
    private LockTemplate lockTemplate;

    /**
     * @Param body 中需要带有已获取的 openId
     */
    @Override
    public UniLoginVo login(String body, SysClientVo client) {
        UniLoginBody loginBody = JsonUtils.parseObject(body, UniLoginBody.class);
        // ValidatorUtils.validate(loginBody); 跳过处理
        String platform = loginBody.getPlatform();
        // xcxCode 为 小程序调用 wx.login 授权后获取
        String xcxCode = loginBody.getCode();
        // 多个小程序识别使用
        String appid = loginBody.getAppId();
        // 租户设置
        uniLoginService.checkTenant(loginBody.getTenantId());
        TenantHelper.setDynamic(loginBody.getTenantId());
        String secret = uniAuthConfigService.getSecret(platform, appid);
        // 校验 appid + appsrcret + xcxCode 调用登录凭证校验接口 获取 session_key 与 openid
        AuthRequest authRequest = new AuthWechatMiniProgramRequest(AuthConfig.builder()
//            .clientId(appid).clientSecret("自行填写密钥 可根据不同appid填入不同密钥")
            .clientId(appid).clientSecret(secret)
            .ignoreCheckRedirectUri(true).ignoreCheckState(true).build());
        AuthCallback authCallback = new AuthCallback();
        authCallback.setCode(xcxCode);
        AuthResponse<AuthUser> resp = authRequest.login(authCallback);
        String openid, unionId;
        if (resp.ok()) {
            AuthToken token = resp.getData().getToken();
            openid = token.getOpenId();
            unionId = token.getUnionId(); // 微信小程序只有关联到微信开放平台下之后才能获取到 unionId，因此unionId不一定能返回。
        } else {
            throw new ServiceException(resp.getMsg());
        }
        // 开始数据处理
        loginBody.setOpenId(openid);
        // 防止多次注册
        final LockInfo lockInfo = lockTemplate.lock(openid, 10000L, 3000L);
        try {
            if (null == lockInfo) {
                throw new RuntimeException("业务繁忙中,请稍后再试");
            }
            // 1 注册
            UniRegisterBo registerBo = JsonUtils.parseObject(body, UniRegisterBo.class);
            registerBo.setOpenId(openid);
            uniLoginService.uniRegister(registerBo);
            // 2 登录
            SysUserVo user = this.loadUserByOpenid(loginBody.getAppId(), loginBody.getOpenId());
            // 3 根据登录用户的数据不同创建 loginUser，属性不够用继承扩展就行了
            UniLoginUser loginUser = getUniLoginUser(client, user, loginBody);
            // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
            // 例如: 后台用户30分钟过期 app用户1天过期
            SaLoginModel model = new SaLoginModel();
            model.setDevice(client.getDeviceType());
            model.setTimeout(client.getTimeout());
            model.setActiveTimeout(client.getActiveTimeout());
            model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
            // 登录与生成token
            LoginHelper.login(loginUser, model);
            // 组织返回对象
            UniLoginVo loginVo = new UniLoginVo();
            loginVo.setAccessToken(StpUtil.getTokenValue());
            loginVo.setExpireIn(StpUtil.getTokenTimeout());
            loginVo.setClientId(client.getClientId());
            loginVo.setOpenid(loginBody.getOpenId());
            return loginVo;
        } finally {
            lockTemplate.releaseLock(lockInfo);
            TenantHelper.clearDynamic();
        }
    }

    @NotNull
    private static UniLoginUser getUniLoginUser(SysClientVo client, SysUserVo user, UniLoginBody loginBody) {
        UniLoginUser loginUser = new UniLoginUser();
        loginUser.setTenantId(user.getTenantId());
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getUserName());
        loginUser.setNickname(user.getNickName());
        loginUser.setUserType(user.getUserType());
        loginUser.setClientKey(client.getClientKey());
        loginUser.setDeviceType(client.getDeviceType());
        loginUser.setOpenid(loginBody.getOpenId());
        // 目前写死，小程序 客户端 的角色
        // SysRoleVo sysRoleVo = DataPermissionHelper.ignore(() -> sysRoleService.selectRoleById(ROLE_MP_VIP_CUSTOMER));
        // RoleDTO roleDTO = BeanUtil.copyProperties(sysRoleVo, RoleDTO.class);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(0L);
        roleDTO.setRoleName("小程序会员");
        roleDTO.setRoleKey(UniRoleKeyEnum.MP_WEIXIN_STR);
        roleDTO.setDataScope(DataScopeType.SELF.getCode());
        loginUser.setRoles(Arrays.asList(roleDTO));
        loginUser.setRolePermission(new HashSet<>(Arrays.asList(roleDTO.getRoleKey())));
        loginUser.setMenuPermission(new HashSet<>());
        return loginUser;
    }

    private SysUserVo loadUserByOpenid(String appId, String openId) {
        SysUserVo user = uniLoginService.uniLogin(appId, openId);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", openId);
            throw new UserException("登录用户不存在");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", openId);
            throw new UserException("登录用户已被停用");
        }
        return user;
    }

}
