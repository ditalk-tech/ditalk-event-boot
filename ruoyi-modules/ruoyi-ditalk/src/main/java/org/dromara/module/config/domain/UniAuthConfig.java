package org.dromara.module.config.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 小程序变量对象 uni_auth_config
 *
 * @author weidixian
 * @date 2025-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("uni_auth_config")
public class UniAuthConfig extends TenantEntity {

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
     * 平台名称
     */
    private String platform;

    /**
     * AppId
     */
    private String appId;

    /**
     * App密钥
     */
    private String secret;

    /**
     * 状态（sys_normal_disable）
     */
    private String state;

    /**
     * 备注信息
     */
    private String remark;


}
