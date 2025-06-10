package org.dromara.uni.web.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.core.domain.model.LoginUser;

import java.io.Serial;

/**
 * DiTalk自定义的Uni小程序登录用户身份权限
 *
 * @author weidixian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UniLoginUser extends LoginUser {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * openid
     */
    private String openid;

}
