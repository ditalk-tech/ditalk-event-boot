package org.dromara.module.member.domain.bo;

import org.dromara.module.member.domain.MemberPhoto;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 会员照片业务对象 member_photo
 *
 * @author weidixian
 * @date 2025-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberPhoto.class, reverseConvertGenerate = false)
public class MemberPhotoBo extends BaseEntity {

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
     * 照片Id
     */
    @NotNull(message = "照片不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long photoId;

    /**
     * 会员ID
     */
    @NotNull(message = "会员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 图片描述
     */
    private String caption;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;


}
