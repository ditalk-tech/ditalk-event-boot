package org.dromara.uni.web.domain.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 客户账号注册信息
 *
 * @author weidixian
 * @date 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UniRegisterBo extends UniLoginBo {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 后台通过code得到的openId
     */
    @NotBlank(message = "openId不能为空")
    private String openId;

    /**
     * 手机号码
     */
    private String phoneNumber;

}
