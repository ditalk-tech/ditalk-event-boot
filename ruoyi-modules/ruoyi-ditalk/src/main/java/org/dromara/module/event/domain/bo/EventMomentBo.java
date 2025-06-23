package org.dromara.module.event.domain.bo;

import org.dromara.module.event.domain.EventMoment;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 活动瞬间业务对象 event_moment
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EventMoment.class, reverseConvertGenerate = false)
public class EventMomentBo extends BaseEntity {

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
     * 图片ID
     */
    @NotNull(message = "图片ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long image;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;


}
