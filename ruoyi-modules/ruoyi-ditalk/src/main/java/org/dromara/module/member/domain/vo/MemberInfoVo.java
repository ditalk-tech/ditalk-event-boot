package org.dromara.module.member.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.module.member.domain.MemberInfo;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 会员信息视图对象
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberInfo.class)
public class MemberInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 乐观锁
     */
    @ExcelProperty(value = "乐观锁")
    private Long version;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    private Long deptId;

    /**
     * 账号
     */
    @ExcelProperty(value = "账号")
    private String userName;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickName;

    /**
     * 用户类型
     */
    @ExcelProperty(value = "用户类型")
    private String userType;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String phoneNumber;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String sex;

    /**
     * 头像ID
     */
    @ExcelProperty(value = "头像ID")
    private Long avatar;

    /**
     * 头像IDUrl
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "avatar")
    private String avatarUrl;
    /**
     * 小程序头像
     */
    @ExcelProperty(value = "小程序头像")
    private String xcxAvatar;

    /**
     * 最后登录IP
     */
    @ExcelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ExcelProperty(value = "最后登录时间")
    private Date loginDate;

    /**
     * 生日
     */
    @ExcelProperty(value = "生日")
    private Date birthday;

    /**
     * 婚姻状态
     */
    @ExcelProperty(value = "婚姻状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ditalk_marital_status")
    private String maritalStatus;

    /**
     * 身高
     */
    @ExcelProperty(value = "身高")
    private Integer tall;

    /**
     * 学历
     */
    @ExcelProperty(value = "学历")
    private String qualification;

    /**
     * 工作
     */
    @ExcelProperty(value = "工作")
    private String career;

    /**
     * 籍贯
     */
    @ExcelProperty(value = "籍贯")
    private String placeOfOrigin;

    /**
     * 爱好
     */
    @ExcelProperty(value = "爱好")
    private String hobby;

    /**
     * 简介
     */
    @ExcelProperty(value = "简介")
    private String profile;

    /**
     * 开放状态
     */
    @ExcelProperty(value = "开放状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ditalk_member_open_state")
    private String openState;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 扩展信息
     */
    @ExcelProperty(value = "扩展信息")
    private String exInfo;


}
