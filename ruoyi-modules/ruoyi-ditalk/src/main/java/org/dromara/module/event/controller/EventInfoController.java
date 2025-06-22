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
import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.domain.bo.EventInfoBo;
import org.dromara.module.event.service.IEventInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动信息
 *
 * @author weidixian
 * @date 2025-06-22
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/event/info")
public class EventInfoController extends BaseController {

    private final IEventInfoService eventInfoService;

    /**
     * 查询活动信息列表
     */
    @SaCheckPermission("event:info:list")
    @GetMapping("/list")
    public TableDataInfo<EventInfoVo> list(EventInfoBo bo, PageQuery pageQuery) {
        return eventInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动信息列表
     */
    @SaCheckPermission("event:info:export")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EventInfoBo bo, HttpServletResponse response) {
        List<EventInfoVo> list = eventInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动信息", EventInfoVo.class, response);
    }

    /**
     * 获取活动信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("event:info:query")
    @GetMapping("/{id}")
    public R<EventInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(eventInfoService.queryById(id));
    }

    /**
     * 新增活动信息
     */
    @SaCheckPermission("event:info:add")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EventInfoBo bo) {
        return toAjax(eventInfoService.insertByBo(bo));
    }

    /**
     * 修改活动信息
     */
    @SaCheckPermission("event:info:edit")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EventInfoBo bo) {
        return toAjax(eventInfoService.updateByBo(bo));
    }

    /**
     * 删除活动信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("event:info:remove")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(eventInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
