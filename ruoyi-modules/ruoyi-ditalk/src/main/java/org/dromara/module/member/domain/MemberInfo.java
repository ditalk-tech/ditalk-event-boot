package org.dromara.module.member.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import java.io.Serial;

/**
 * 会员信息对象 member_info
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_info")
public class MemberInfo extends TenantEntity {

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
     * 部门ID
     */
    private Long deptId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户类型
     */
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
     * 婚姻状况
     */
    private String maritalStatus;

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
