package org.dromara.module.contact.domain.bo;

import org.dromara.module.contact.domain.ContactInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 联系人业务对象 contact_info
 *
 * @author weidixian
 * @date 2025-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ContactInfo.class, reverseConvertGenerate = false)
public class ContactInfoBo extends BaseEntity {

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
     * 名字
     */
    @NotBlank(message = "名字不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名拼音
     */
    private String pinyin;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 户籍
     */
    private String placeOfOrigin;

    /**
     * 居住地
     */
    private String address;

    /**
     * 毕业学校
     */
    private String graduationSchool;

    /**
     * 学历
     */
    private String qualification;

    /**
     * 公司组织
     */
    private String organization;

    /**
     * 工作职务
     */
    private String position;

    /**
     * 社会角色
     */
    private String socialRole;

    /**
     * 描述
     */
    private String description;

    /**
     * 最近接触时间
     */
    private Date lastInteractionTime;

    /**
     * 接触频率
     */
    private String interactionFrequency;


}
