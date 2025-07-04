package org.dromara.module.event.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 活动瞬间对象 event_moment
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("event_moment")
public class EventMoment extends TenantEntity {

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
     * 活动ID
     */
    private Long eventId;

    /**
     * 图片ID
     */
    private Long image;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 状态
     */
    private String state;


}
