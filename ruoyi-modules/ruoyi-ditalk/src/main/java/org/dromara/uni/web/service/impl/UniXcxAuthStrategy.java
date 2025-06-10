package org.dromara.uni.web.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.dto.RoleDTO;
import org.dromara.common.core.enums.UserStatus;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.mybatis.enums.DataScopeType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.system.domain.vo.SysClientVo;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.uni.web.domain.bo.UniLoginBody;
import org.dromara.uni.web.domain.bo.UniLoginUser;
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

    /**
     * @Param body 中需要带有已获取的 openId
     */
    @Override
    public UniLoginVo login(String body, SysClientVo client) {
        UniLoginBody loginBody = JsonUtils.parseObject(body, UniLoginBody.class);
        // ValidatorUtils.validate(loginBody);
        // 框架登录不限制从什么表查询 只要最终构建出 LoginUser 即可
        SysUserVo user = this.loadUserByOpenid(loginBody.getAppId(), loginBody.getOpenId());
        if (user == null) {
            throw new UserException("用户不存在");
        }
        // 此处可根据登录用户的数据不同 自行创建 loginUser 属性不够用继承扩展就行了
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
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", openId);
        }
        return user;
    }

}
