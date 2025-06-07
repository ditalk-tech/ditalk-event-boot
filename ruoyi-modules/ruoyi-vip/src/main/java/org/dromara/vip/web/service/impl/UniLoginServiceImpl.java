package org.dromara.vip.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.constant.SystemConstants;
import org.dromara.common.core.constant.TenantConstants;
import org.dromara.common.core.enums.UserType;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.tenant.exception.TenantException;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.system.domain.vo.SysTenantVo;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.system.service.ISysTenantService;
import org.dromara.vip.common.constant.CommonConstants;
import org.dromara.vip.common.utils.ConversionUtil;
import org.dromara.vip.member.domain.bo.MemberInfoBo;
import org.dromara.vip.member.domain.bo.MemberOpenidBo;
import org.dromara.vip.member.domain.vo.MemberInfoVo;
import org.dromara.vip.member.domain.vo.MemberOpenidVo;
import org.dromara.vip.member.service.IMemberInfoService;
import org.dromara.vip.member.service.IMemberOpenidService;
import org.dromara.vip.web.domain.bo.UniRegisterBo;
import org.dromara.vip.web.service.IUniLoginService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 登录校验方法
 *
 * @author weidixian
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UniLoginServiceImpl implements IUniLoginService {

    private final ISysTenantService tenantService;
    private final IMemberInfoService memberInfoService;
    private final IMemberOpenidService memberOpenidService;

    private SysUserVo toSysUserVo(MemberInfoVo infoVo) {
        return getSysUserVo(infoVo.getId(), infoVo.getUserName(), infoVo.getNickName(), infoVo.getUserType());
    }

    @NotNull
    private SysUserVo getSysUserVo(Long id, String userName, String nickName, String userType) {
        SysUserVo user = new SysUserVo();
        user.setTenantId(TenantHelper.getTenantId());
        user.setUserId(id);
        user.setUserName(userName);
        user.setNickName(nickName);
        user.setUserType(userType);
        return user;
    }

    /**
     * 校验租户
     *
     * @param tenantId 租户ID
     */
    @Override
    public void checkTenant(String tenantId) {
        if (!TenantHelper.isEnable()) {
            return;
        }
        if (StringUtils.isBlank(tenantId)) {
            throw new TenantException("tenant.number.not.blank");
        }
        if (TenantConstants.DEFAULT_TENANT_ID.equals(tenantId)) {
            return;
        }
        SysTenantVo tenant = tenantService.queryByTenantId(tenantId);
        if (ObjectUtil.isNull(tenant)) {
            log.info("登录租户：{} 不存在.", tenantId);
            throw new TenantException("tenant.not.exists");
        } else if (SystemConstants.DISABLE.equals(tenant.getStatus())) {
            log.info("登录租户：{} 已被停用.", tenantId);
            throw new TenantException("tenant.blocked");
        } else if (ObjectUtil.isNotNull(tenant.getExpireTime())
                && new Date().after(tenant.getExpireTime())) {
            log.info("登录租户：{} 已超过有效期.", tenantId);
            throw new TenantException("tenant.expired");
        }
    }

    @Override
    public SysUserVo uniLogin(String appId, String openId) {
        // 获取绑定的openId账户
        MemberOpenidVo openidVo = memberOpenidService.queryByOpenInfo(appId, openId);
        if (openidVo != null) {
            // MemberOpenid 状态不可用
            if (openidVo.getState().equals(CommonConstants.UNAVAILABLE)) {
                throw new UserException("common.account.state.unavailable");
            }
            MemberInfoVo infoVo = memberInfoService.queryById(openidVo.getMemberId());
            if (infoVo != null) {
                // MemberInfo 状态不可用
                if (infoVo.getState().equals(CommonConstants.UNAVAILABLE)) {
                    throw new UserException("common.account.state.unavailable");
                }
                return toSysUserVo(infoVo);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    @DSTransactional
    public void uniRegister(UniRegisterBo uniRegisterBo) {
        MemberOpenidVo openidVo = memberOpenidService.queryByOpenInfo(uniRegisterBo.getAppId(), uniRegisterBo.getOpenId());
        if (openidVo != null) {
            return;
        }
        long id = IdWorker.getId();
        String userName = ConversionUtil.numberToBase32(id);
        // 保存用户账户
        MemberInfoBo memberInfoBo = new MemberInfoBo();
        memberInfoBo.setId(id);
        memberInfoBo.setUserType(UserType.APP_USER.getUserType());
        memberInfoBo.setUserName(userName);
        memberInfoBo.setNickName(uniRegisterBo.getNickName());
        memberInfoBo.setPhoneNumber(uniRegisterBo.getPhoneNumber());
        memberInfoBo.setXcxAvatar(uniRegisterBo.getAvatar());
        memberInfoBo.setState(CommonConstants.AVAILABLE);
        memberInfoBo.setCreateBy(id);
        memberInfoBo.setCreateTime(new Date());
        memberInfoService.insertByBo(memberInfoBo);
        // 保存OpenId账户
        MemberOpenidBo memberOpenidBo = new MemberOpenidBo();
        memberOpenidBo.setMemberId(memberInfoBo.getId());
        memberOpenidBo.setAppId(uniRegisterBo.getAppId());
        memberOpenidBo.setPlatform(uniRegisterBo.getPlatform());
        memberOpenidBo.setOpenId(uniRegisterBo.getOpenId());
        memberOpenidBo.setState(CommonConstants.AVAILABLE);
        memberOpenidBo.setCreateBy(id);
        memberOpenidBo.setCreateTime(new Date());
        memberOpenidService.insertByBo(memberOpenidBo);
    }

}
