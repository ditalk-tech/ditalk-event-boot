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
import org.dromara.module.event.domain.vo.EventMemberVo;
import org.dromara.module.event.domain.bo.EventMemberBo;
import org.dromara.module.event.service.IEventMemberService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动报名人
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/event/member")
public class EventMemberController extends BaseController {

    private final IEventMemberService eventMemberService;

    /**
     * 查询活动报名人列表
     */
    @SaCheckPermission("event:member:list")
    @GetMapping("/list")
    public TableDataInfo<EventMemberVo> list(EventMemberBo bo, PageQuery pageQuery) {
        return eventMemberService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动报名人列表
     */
    @SaCheckPermission("event:member:export")
    @Log(title = "活动报名人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EventMemberBo bo, HttpServletResponse response) {
        List<EventMemberVo> list = eventMemberService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动报名人", EventMemberVo.class, response);
    }

    /**
     * 获取活动报名人详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("event:member:query")
    @GetMapping("/{id}")
    public R<EventMemberVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(eventMemberService.queryById(id));
    }

    /**
     * 新增活动报名人
     */
    @SaCheckPermission("event:member:add")
    @Log(title = "活动报名人", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EventMemberBo bo) {
        return toAjax(eventMemberService.insertByBo(bo));
    }

    /**
     * 修改活动报名人
     */
    @SaCheckPermission("event:member:edit")
    @Log(title = "活动报名人", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EventMemberBo bo) {
        return toAjax(eventMemberService.updateByBo(bo));
    }

    /**
     * 删除活动报名人
     *
     * @param ids 主键串
     */
    @SaCheckPermission("event:member:remove")
    @Log(title = "活动报名人", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(eventMemberService.deleteWithValidByIds(List.of(ids), true));
    }
}
