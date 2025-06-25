package org.dromara.uni.controller.domain.vo;

import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.module.member.domain.vo.MemberInfoVo;


/**
 * 会员信息扩展视图对象
 *
 * @author weidixian
 */
@Data
public class MemberInfoExtVo extends MemberInfoVo {

    /**
     * 性别标签
     */
    @Translation(type = TransConstant.DICT_TYPE_TO_LABEL, mapper = "sex", other = "sys_user_sex")
    private String sexLabel;

    /**
     * 婚姻状态标签
     */
    @Translation(type = TransConstant.DICT_TYPE_TO_LABEL, mapper = "maritalStatus", other = "ditalk_marital_status")
    private String maritalStatusLabel;

    /**
     * 开放状态标签
     */
    @Translation(type = TransConstant.DICT_TYPE_TO_LABEL, mapper = "openState", other = "ditalk_member_open_state")
    private String openStateLabel;

}
