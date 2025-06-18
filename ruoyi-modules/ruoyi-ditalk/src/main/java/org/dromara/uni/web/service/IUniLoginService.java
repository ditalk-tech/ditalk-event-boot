package org.dromara.uni.web.service;

import jakarta.validation.constraints.NotBlank;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.uni.web.domain.bo.UniRegisterBo;

public interface IUniLoginService {

    SysUserVo uniLogin(String appId, String openId);

    void checkTenant(@NotBlank String tenantId);

    /**
     * 注册，如果账号已存在直接返回不做操作
     *
     * @param registerBo 注册信息
     */
    void uniRegister(UniRegisterBo registerBo);
}
