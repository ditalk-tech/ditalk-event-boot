package org.dromara.module.member.domain.bo;

import org.dromara.module.member.domain.MemberInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 会员信息业务对象 member_info
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberInfo.class, reverseConvertGenerate = false)
public class MemberInfoBo extends BaseEntity {

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
     * 部门ID
     */
    private Long deptId;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空", groups = { AddGroup.class })
    private String userName;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nickName;

    /**
     * 用户类型
     */
    @NotBlank(message = "用户类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像ID
     */
    private Long avatar;

    /**
     * 小程序头像
     */
    private String xcxAvatar;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身高
     */
    private Integer tall;

    /**
     * 学历
     */
    private String qualification;

    /**
     * 工作
     */
    private String career;

    /**
     * 籍贯
     */
    private String placeOfOrigin;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 简介
     */
    private String profile;

    /**
     * 开放状态
     */
    private String openState;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展信息
     */
    private String exInfo;


}
