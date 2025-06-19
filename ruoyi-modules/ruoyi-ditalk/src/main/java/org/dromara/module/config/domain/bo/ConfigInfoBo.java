package org.dromara.module.config.domain.bo;

import org.dromara.module.config.domain.ConfigInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 配置信息业务对象 config_info
 *
 * @author weidixian
 * @date 2025-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ConfigInfo.class, reverseConvertGenerate = false)
public class ConfigInfoBo extends BaseEntity {

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
     * 状态（sys_normal_disable）
     */
    @NotBlank(message = "状态（sys_normal_disable）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * 配置项名称
     */
    @NotBlank(message = "配置项名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 配置项键
     */
    @NotBlank(message = "配置项键不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 配置项JSON值
     */
    @NotBlank(message = "配置项JSON值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String value;

    /**
     * 备注
     */
    private String remark;


}
