package org.dromara.vip.web.service;

import jakarta.validation.constraints.NotBlank;
import org.dromara.system.domain.vo.SysUserVo;
import org.dromara.vip.web.domain.bo.UniRegisterBo;

public interface IUniLoginService {

    SysUserVo uniLogin(String appId, String openId);

    void checkTenant(@NotBlank String tenantId);

    void uniRegister(UniRegisterBo registerBo);
}
