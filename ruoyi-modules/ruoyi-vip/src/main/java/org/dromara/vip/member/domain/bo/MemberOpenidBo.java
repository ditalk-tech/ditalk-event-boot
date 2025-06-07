package org.dromara.vip.member.domain.bo;

import org.dromara.vip.member.domain.MemberOpenid;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 会员OpenId业务对象 member_openid
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberOpenid.class, reverseConvertGenerate = false)
public class MemberOpenidBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 乐观锁
     */
    @NotNull(message = "乐观锁不能为空", groups = { EditGroup.class })
    private Long version;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * 会员ID
     */
    @NotNull(message = "会员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * App应用ID
     */
    @NotBlank(message = "App应用ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 平台代码
     */
    @NotBlank(message = "平台代码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String platform;

    /**
     * OpenID
     */
    @NotBlank(message = "OpenID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String openId;


}
