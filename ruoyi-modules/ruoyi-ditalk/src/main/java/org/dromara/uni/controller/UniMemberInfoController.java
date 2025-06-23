package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.bean.BeanUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.utils.AgeRangeCalculator;
import org.dromara.common.web.core.BaseController;
import org.dromara.module.member.domain.vo.MemberInfoVo;
import org.dromara.module.member.service.IMemberInfoService;
import org.dromara.uni.controller.domain.vo.MemberInfoBasicVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
