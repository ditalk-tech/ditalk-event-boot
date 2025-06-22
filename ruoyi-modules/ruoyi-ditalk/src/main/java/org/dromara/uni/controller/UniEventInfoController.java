package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.web.core.BaseController;
import org.dromara.module.event.domain.bo.EventInfoBo;
import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.service.IEventInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动事件
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/event/info")
public class UniEventInfoController extends BaseController {

    private final IEventInfoService eventInfoService;

    /**
     * 查询列表
     *
     * @param pageQuery 分页参数
     * @return 列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/list")
    public R<List<EventInfoVo>> list(IdPageQuery pageQuery) {
        EventInfoBo eventInfoBo = new EventInfoBo();
        eventInfoBo.setState(CommonConstants.AVAILABLE);
        return R.ok(eventInfoService.queryList(eventInfoBo, pageQuery));
    }

    /**
     * 获取详细信息
     *
     * @param id 主键
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/{id}")
    public R<EventInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long id) {
        return R.ok(eventInfoService.queryById(id));
    }

    /**
     * 查询列表 —— 最新活动列表
     *
     * @param pageQuery 分页参数
     * @return 列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/new/list")
    public R<List<EventInfoVo>> newList(IdPageQuery pageQuery) {
        EventInfoBo eventInfoBo = new EventInfoBo();
        Map<String, Object> params = new HashMap<>();
        params.put("getNewList", 0);
        eventInfoBo.setParams(params);
        eventInfoBo.setState(CommonConstants.AVAILABLE);
        return R.ok(eventInfoService.queryList(eventInfoBo, pageQuery));
    }

    /**
     * 查询列表 —— 历史活动列表
     *
     * @param pageQuery 分页参数
     * @return 列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/old/list")
    public R<List<EventInfoVo>> oldList(IdPageQuery pageQuery) {
        EventInfoBo eventInfoBo = new EventInfoBo();
        Map<String, Object> params = new HashMap<>();
        params.put("getOldList", 0);
        eventInfoBo.setParams(params);
        eventInfoBo.setState(CommonConstants.AVAILABLE);
        return R.ok(eventInfoService.queryList(eventInfoBo, pageQuery));
    }

//    /**
//     * 新增
//     *
//     * @param bo 新增对象
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @Log(title = "活动事件", businessType = BusinessType.INSERT)
//    @RepeatSubmit()
//    @PostMapping()
//    public R<Void> add(@Validated(AddGroup.class) @RequestBody EventInfoBo bo) {
//        return toAjax(eventInfoService.insertByBo(bo));
//    }
//
//    /**
//     * 修改
//     *
//     * @param bo 修改对象
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @Log(title = "活动事件", businessType = BusinessType.UPDATE)
//    @RepeatSubmit()
//    @PutMapping()
//    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EventInfoBo bo) {
//        return toAjax(eventInfoService.updateByBo(bo));
//    }
//
//    /**
//     * 删除
//     *
//     * @param ids 主键串
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @Log(title = "活动事件", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public R<Void> remove(@NotEmpty(message = "主键不能为空")
//                          @PathVariable Long[] ids) {
//        return toAjax(eventInfoService.deleteWithValidByIds(List.of(ids), true));
//    }

}
