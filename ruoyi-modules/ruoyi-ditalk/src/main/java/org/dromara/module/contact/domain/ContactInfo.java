package org.dromara.module.contact.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 联系人对象 contact_info
 *
 * @author weidixian
 * @date 2025-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("contact_info")
public class ContactInfo extends TenantEntity {

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
     * 状态
     */
    private String state;

    /**
     * 名字
     */
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
