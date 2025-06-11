package org.dromara.module.contact.domain.bo;

import org.dromara.module.contact.domain.ContactLog;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 联系记录业务对象 contact_log
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ContactLog.class, reverseConvertGenerate = false)
public class ContactLogBo extends BaseEntity {

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
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * 联系人ID
     */
    @NotNull(message = "联系人ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long contactId;

    /**
     * 经办人ID
     */
    @NotNull(message = "经办人ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long operatorId;

    /**
     * 通讯渠道
     */
    @NotBlank(message = "通讯渠道不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channel;

    /**
     * 主题
     */
    @NotBlank(message = "主题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String subject;

    /**
     * 描述
     */
    private String description;


}
