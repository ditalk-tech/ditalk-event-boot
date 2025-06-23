package org.dromara.module.event.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.module.event.domain.vo.EventMomentVo;
import org.dromara.module.event.domain.bo.EventMomentBo;
import org.dromara.module.event.service.IEventMomentService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动瞬间
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/event/moment")
public class EventMomentController extends BaseController {

    private final IEventMomentService eventMomentService;

    /**
     * 查询活动瞬间列表
     */
    @SaCheckPermission("event:moment:list")
    @GetMapping("/list")
    public TableDataInfo<EventMomentVo> list(EventMomentBo bo, PageQuery pageQuery) {
        return eventMomentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动瞬间列表
     */
    @SaCheckPermission("event:moment:export")
    @Log(title = "活动瞬间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EventMomentBo bo, HttpServletResponse response) {
        List<EventMomentVo> list = eventMomentService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动瞬间", EventMomentVo.class, response);
    }

    /**
     * 获取活动瞬间详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("event:moment:query")
    @GetMapping("/{id}")
    public R<EventMomentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(eventMomentService.queryById(id));
    }

    /**
     * 新增活动瞬间
     */
    @SaCheckPermission("event:moment:add")
    @Log(title = "活动瞬间", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EventMomentBo bo) {
        return toAjax(eventMomentService.insertByBo(bo));
    }

    /**
     * 修改活动瞬间
     */
    @SaCheckPermission("event:moment:edit")
    @Log(title = "活动瞬间", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EventMomentBo bo) {
        return toAjax(eventMomentService.updateByBo(bo));
    }

    /**
     * 删除活动瞬间
     *
     * @param ids 主键串
     */
    @SaCheckPermission("event:moment:remove")
    @Log(title = "活动瞬间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(eventMomentService.deleteWithValidByIds(List.of(ids), true));
    }
}
