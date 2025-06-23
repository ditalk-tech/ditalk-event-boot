package org.dromara.uni.controller.domain.vo;

import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 会员基础信息视图对象 member_info
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
public class MemberInfoBasicVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 记录状态
     */
    private String state;

//    /**
//     * 账号
//     */
//    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

//    /**
//     * 手机号码
//     */
//    private String phoneNumber;

    /**
     * 性别值
     */
    private String sex;

    /**
     * 性别标签
     */
    @Translation(type = TransConstant.DICT_TYPE_TO_LABEL, mapper = "sex", other = "sys_user_sex")
    private String sexLabel;

    /**
     * 头像ID
     */
    private Long avatar;

    /**
     * 头像IDUrl
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "avatar")
    private String avatarUrl;

//    /**
//     * 小程序头像
//     */
//    private String xcxAvatar;

//    /**
//     * 生日
//     */
//    private Date birthday;

    /**
     * 年龄段
     */
    private String ageRange;

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
     * 开放状态标签
     */
    @Translation(type = TransConstant.DICT_TYPE_TO_LABEL, mapper = "openState", other = "ditalk_member_open_state")
    private String openStateLabel;

//    /**
//     * 备注
//     */
//    private String remark;
//
//    /**
//     * 扩展信息
//     */
//    private String exInfo;

}
