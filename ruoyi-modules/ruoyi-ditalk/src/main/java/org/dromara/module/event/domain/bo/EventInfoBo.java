package org.dromara.module.event.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.module.event.domain.EventInfo;

import java.util.Date;

/**
 * 活动信息业务对象 event_info
 *
 * @author weidixian
 * @date 2025-06-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EventInfo.class, reverseConvertGenerate = false)
public class EventInfoBo extends BaseEntity {

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
     * 封面图片
     */
    @NotNull(message = "封面图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long coverImage;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 发布时间
     */
    @NotNull(message = "发布时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date publishTime;

    /**
     * 报名截止
     */
    @NotNull(message = "报名截止不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date applicationDeadline;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startTime;

    /**
     * 名额
     */
    @NotNull(message = "名额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long quota;

    /**
     * 地点
     */
    @NotNull(message = "地点不能为空", groups = { AddGroup.class, EditGroup.class })
    private String location;

    /**
     * 活动安排
     */
    @NotBlank(message = "活动安排不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;


}
