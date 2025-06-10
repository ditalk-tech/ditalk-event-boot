package org.dromara.uni.web.service;

import jakarta.validation.constraints.NotBlank;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.uni.web.domain.bo.UniRegisterBo;

public interface IUniLoginService {

    SysUserVo uniLogin(String appId, String openId);

    void checkTenant(@NotBlank String tenantId);

    void uniRegister(UniRegisterBo registerBo);
}
