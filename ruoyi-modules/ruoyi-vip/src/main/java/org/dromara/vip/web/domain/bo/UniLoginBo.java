package org.dromara.vip.web.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.dromara.common.core.xss.Xss;

import java.io.Serial;
import java.io.Serializable;

/**
 * 客户账号注册信息
 *
 * @author weidixian
 * @date 2023-12-28
 */
@Data
//@EqualsAndHashCode(callSuper=false)
public class UniLoginBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @NotBlank(message = "{auth.clientid.not.blank}")
    private String clientId;

    /**
     * 授权类型
     */
    @NotBlank(message = "{auth.grant.type.not.blank}")
    private String grantType;

    /**
     * 租户ID
     */
    @NotBlank(message = "租户ID不能为空")
    private String tenantId;

    /**
     * App应用ID
     */
    @NotBlank(message = "App应用ID不能为空")
    private String appId;

    /**
     * 平台代码
     */
    private String platform;

    /**
     * 客户端成功获取授权临时票据（code）
     */
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 后台通过code得到的openId
     */
    private String openId;

    /**
     * 客户账号
     */
    @Xss(message = "客户账号不能包含脚本字符")
    @NotBlank(message = "客户账号不能为空")
    @Size(min = 0, max = 30, message = "客户账号长度不能超过{max}个字符")
    private String userName;

    /**
     * 客户昵称
     */
    @Xss(message = "客户昵称不能包含脚本字符")
    @NotBlank(message = "客户昵称不能为空")
    @Size(min = 0, max = 30, message = "客户昵称长度不能超过{max}个字符")
    private String nickName;

    /**
     * 头像地址
     */
    @Xss(message = "头像地址不能包含脚本字符")
    private String avatar;

}
