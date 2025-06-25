package org.dromara.module.event.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 活动报名人对象 event_member
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("event_member")
public class EventMember extends TenantEntity {

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
     * 会员ID
     */
    private Long memberId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 签到码
     */
    private Integer signCode;

    /**
     * 状态
     */
    private String state;


}
