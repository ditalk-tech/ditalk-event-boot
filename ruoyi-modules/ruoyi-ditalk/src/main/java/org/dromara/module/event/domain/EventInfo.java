package org.dromara.module.event.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import java.io.Serial;

/**
 * 活动信息对象 event_info
 *
 * @author weidixian
 * @date 2025-06-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("event_info")
public class EventInfo extends TenantEntity {

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
     * 封面图片
     */
    private Long coverImage;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 报名截止
     */
    private Date applicationDeadline;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 名额
     */
    private Long quota;

    /**
     * 地点
     */
    private String location;

    /**
     * 活动安排
     */
    private String arrangement;

    /**
     * 报名会员快照
     */
    private String members;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 扩展信息
     */
    private String exInfo;

    /**
     * 状态
     */
    private String state;


}
