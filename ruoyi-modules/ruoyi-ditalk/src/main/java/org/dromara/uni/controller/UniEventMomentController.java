package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.web.core.BaseController;
import org.dromara.module.event.domain.bo.EventMomentBo;
import org.dromara.module.event.domain.vo.EventMomentVo;
import org.dromara.module.event.service.IEventMomentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 活动瞬间
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/event/moment")
public class UniEventMomentController extends BaseController {

    private final IEventMomentService eventMomentService;

    /**
     * 查询列表
     *
     * @param pageQuery 分页参数
     * @return 列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/list")
    public R<List<EventMomentVo>> list(IdPageQuery pageQuery) {
        EventMomentBo eventMomentBo = new EventMomentBo();
        eventMomentBo.setState(CommonConstants.AVAILABLE);
        return R.ok(eventMomentService.queryList(eventMomentBo, pageQuery));
    }

    /**
     * 获取详细信息
     *
     * @param id 主键
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/{id}")
    public R<EventMomentVo> getDetail(@NotNull(message = "主键不能为空")
                                      @PathVariable Long id) {
        return R.ok(eventMomentService.queryById(id));
    }

//    /**
//     * 新增
//     *
//     * @param bo 新增对象
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @Log(title = "活动瞬间", businessType = BusinessType.INSERT)
//    @RepeatSubmit()
//    @PostMapping()
//    public R<Void> add(@Validated(AddGroup.class) @RequestBody EventMomentBo bo) {
//        return toAjax(eventMomentService.insertByBo(bo));
//    }
//
//    /**
//     * 修改
//     *
//     * @param bo 修改对象
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @Log(title = "活动瞬间", businessType = BusinessType.UPDATE)
//    @RepeatSubmit()
//    @PutMapping()
//    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EventMomentBo bo) {
//        return toAjax(eventMomentService.updateByBo(bo));
//    }
//
//    /**
//     * 删除
//     *
//     * @param ids 主键串
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @Log(title = "活动瞬间", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public R<Void> remove(@NotEmpty(message = "主键不能为空")
//                          @PathVariable Long[] ids) {
//        return toAjax(eventMomentService.deleteWithValidByIds(List.of(ids), true));
//    }

}
