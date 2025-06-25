package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.bean.BeanUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.utils.AgeRangeCalculator;
import org.dromara.common.web.core.BaseController;
import org.dromara.module.member.domain.bo.MemberInfoBo;
import org.dromara.module.member.domain.vo.MemberInfoVo;
import org.dromara.module.member.service.IMemberInfoService;
import org.dromara.uni.controller.domain.vo.MemberInfoBasicVo;
import org.dromara.uni.controller.domain.vo.MemberInfoExtVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 会员信息
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/member/info")
public class UniMemberInfoController extends BaseController {

    private final IMemberInfoService memberInfoService;

//    /**
//     * 查询列表
//     *
//     * @param pageQuery 分页参数
//     * @return 列表
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @GetMapping("/list")
//    public R<List<MemberInfoVo>> list(IdPageQuery pageQuery) {
//        MemberInfoBo memberInfoBo = new MemberInfoBo();
//        memberInfoBo.setState(CommonConstants.AVAILABLE);
//        return R.ok(memberInfoService.queryList(memberInfoBo, pageQuery));
//    }

    /**
     * 获取简版详细信息
     *
     * @param id 主键
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/basic/{id}")
    public R<MemberInfoBasicVo> getDetail(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        MemberInfoVo memberInfoVo = memberInfoService.queryById(id);
        if (memberInfoVo == null) {
            throw new UserException("信息不存在");
        }
        MemberInfoBasicVo memberInfoBasicVo = BeanUtil.copyProperties(memberInfoVo, MemberInfoBasicVo.class);
        if (memberInfoVo.getBirthday() != null) {
            memberInfoBasicVo.setAgeRange(AgeRangeCalculator.calculateAgeRange(memberInfoVo.getBirthday()));
        }
        return R.ok(memberInfoBasicVo);
    }

    /**
     * 获取本人的详细信息
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/my")
    public R<MemberInfoExtVo> getMyDetail() {
        MemberInfoVo memberInfoVo = memberInfoService.queryById(LoginHelper.getUserId());
        if (memberInfoVo == null) {
            throw new UserException("信息不存在");
        }
        return R.ok(BeanUtil.copyProperties(memberInfoVo, MemberInfoExtVo.class));
    }

    /**
     * 获取本人的详细信息
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/my")
    public R updateMyInfo(@Validated(EditGroup.class) @RequestBody MemberInfoBo bo) {
        if (!bo.getId().equals(LoginHelper.getUserId())) {
            throw new UserException("无权操作");
        }
        return toAjax(memberInfoService.updateByBo(bo));
    }

}
