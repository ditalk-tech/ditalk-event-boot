package org.dromara.module.contact.domain.bo;

import org.dromara.module.contact.domain.ContactTags;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 联系人标签业务对象 contact_tags
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ContactTags.class, reverseConvertGenerate = false)
public class ContactTagsBo extends BaseEntity {

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
     * 标签分类
     */
    @NotBlank(message = "标签分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String category;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;


}
