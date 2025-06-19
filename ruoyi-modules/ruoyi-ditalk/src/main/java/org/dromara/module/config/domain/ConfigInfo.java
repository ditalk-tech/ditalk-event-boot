package org.dromara.module.config.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 配置信息对象 config_info
 *
 * @author weidixian
 * @date 2025-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("config_info")
public class ConfigInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 状态（sys_normal_disable）
     */
    private String state;

    /**
     * 配置项名称
     */
    private String name;

    /**
     * 配置项键
     */
    private String code;

    /**
     * 配置项JSON值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;


}
