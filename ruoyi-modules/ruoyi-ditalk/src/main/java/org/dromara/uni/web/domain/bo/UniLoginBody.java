package org.dromara.uni.web.domain.bo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.dromara.common.core.domain.model.LoginBody;

/**
 * DiTalk自定义的Uni小程序的登录对象
 *
 * @author weidixian
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UniLoginBody extends LoginBody {

    /**
     * 小程序平台类型
     */
    @NotNull
    private String platform;

    /**
     * openId
     */
    @NotNull
    private String openId;

    /**
     * 小程序id(多个小程序时使用)
     */
    @NotNull
    private String appId;

}
