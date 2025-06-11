package org.dromara.module.contact.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 联系人标签对象 contact_tags
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("contact_tags")
public class ContactTags extends TenantEntity {

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
     * 状态
     */
    private String state;

    /**
     * 联系人ID
     */
    private Long contactId;

    /**
     * 标签分类
     */
    private String category;

    /**
     * 标签名称
     */
    private String name;


}
