package org.dromara.module.config.domain.bo;

import org.dromara.module.config.domain.UniAuthConfig;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 小程序变量业务对象 uni_auth_config
 *
 * @author weidixian
 * @date 2025-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = UniAuthConfig.class, reverseConvertGenerate = false)
public class UniAuthConfigBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 乐观锁
     */
    @NotNull(message = "乐观锁不能为空", groups = { EditGroup.class })
    private Long version;

    /**
     * 平台名称
     */
    @NotBlank(message = "平台名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String platform;

    /**
     * AppId
     */
    @NotBlank(message = "AppId不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * App密钥
     */
    @NotBlank(message = "App密钥不能为空", groups = { AddGroup.class, EditGroup.class })
    private String secret;

    /**
     * 状态（sys_normal_disable）
     */
    @NotBlank(message = "状态（sys_normal_disable）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * 备注信息
     */
    private String remark;


}
