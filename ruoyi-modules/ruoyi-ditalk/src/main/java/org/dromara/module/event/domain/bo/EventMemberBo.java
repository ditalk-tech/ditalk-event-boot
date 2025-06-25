package org.dromara.module.event.domain.bo;

import org.dromara.module.event.domain.EventMember;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.Date;

/**
 * 活动报名人业务对象 event_member
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EventMember.class, reverseConvertGenerate = false)
public class EventMemberBo extends BaseEntity {

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
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long eventId;

    /**
     * 会员ID
     */
    @NotNull(message = "会员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startTime;

    /**
     * 签到码
     */
    @NotNull(message = "签到码不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer signCode;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;


}
