package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.module.member.domain.bo.MemberPhotoBo;
import org.dromara.module.member.domain.vo.MemberPhotoVo;
import org.dromara.module.member.service.IMemberPhotoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员照片
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/member/photo")
public class UniMemberPhotoController extends BaseController {

    private final IMemberPhotoService memberPhotoService;

    /**
     * 查询指定用户的照片列表
     *
     * @param pageQuery 分页参数
     * @return 列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/list/{memberId}")
    public R<List<MemberPhotoVo>> listMember(@NotNull(message = "参数不能为空")
                                       @PathVariable Long memberId, IdPageQuery pageQuery) {
        MemberPhotoBo memberPhotoBo = new MemberPhotoBo();
        memberPhotoBo.setMemberId(memberId);
        memberPhotoBo.setState(CommonConstants.AVAILABLE);
        return R.ok(memberPhotoService.queryList(memberPhotoBo, pageQuery));
    }

    /**
     * 查询本人的照片列表
     *
     * @param pageQuery 分页参数
     * @return 列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/list/my")
    public R<List<MemberPhotoVo>> listMy(IdPageQuery pageQuery) {
        MemberPhotoBo memberPhotoBo = new MemberPhotoBo();
        memberPhotoBo.setMemberId(LoginHelper.getUserId());
        memberPhotoBo.setState(CommonConstants.AVAILABLE);
        return R.ok(memberPhotoService.queryList(memberPhotoBo, pageQuery));
    }

    /**
     * 获取详细信息
     *
     * @param id 主键
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/{id}")
    public R<MemberPhotoVo> getDetail(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        MemberPhotoVo memberPhotoVo = memberPhotoService.queryById(id);
        return R.ok(memberPhotoVo);
    }

    /**
     * 新增
     *
     * @param bo 新增对象
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "会员照片", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberPhotoBo bo) {
        if (!LoginHelper.getUserId().equals(bo.getMemberId())) {
            throw new UserException("无权操作");
        }
        return toAjax(memberPhotoService.insertByBo(bo));
    }

    /**
     * 修改
     *
     * @param bo 修改对象
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "会员照片", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberPhotoBo bo) {
        if (!LoginHelper.getUserId().equals(bo.getMemberId())) {
            throw new UserException("无权操作");
        }
        return toAjax(memberPhotoService.updateByBo(bo));
    }

    /**
     * 删除
     *
     * @param ids 主键串
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "会员照片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        for (Long id : ids) {
            MemberPhotoVo memberPhotoVo = memberPhotoService.queryById(id);
            if (memberPhotoVo == null || !LoginHelper.getUserId().equals(memberPhotoVo.getMemberId())) {
                throw new UserException("无权操作");
            }
        }
        return toAjax(memberPhotoService.deleteWithValidByIds(List.of(ids), true));
    }

}
