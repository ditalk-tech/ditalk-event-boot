package org.dromara.module.contact.controller;

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
import org.dromara.module.contact.domain.vo.ContactLogVo;
import org.dromara.module.contact.domain.bo.ContactLogBo;
import org.dromara.module.contact.service.IContactLogService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 联系记录
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contact/log")
public class ContactLogController extends BaseController {

    private final IContactLogService contactLogService;

    /**
     * 查询联系记录列表
     */
    @SaCheckPermission("contact:log:list")
    @GetMapping("/list")
    public TableDataInfo<ContactLogVo> list(ContactLogBo bo, PageQuery pageQuery) {
        return contactLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出联系记录列表
     */
    @SaCheckPermission("contact:log:export")
    @Log(title = "联系记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ContactLogBo bo, HttpServletResponse response) {
        List<ContactLogVo> list = contactLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "联系记录", ContactLogVo.class, response);
    }

    /**
     * 获取联系记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contact:log:query")
    @GetMapping("/{id}")
    public R<ContactLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(contactLogService.queryById(id));
    }

    /**
     * 新增联系记录
     */
    @SaCheckPermission("contact:log:add")
    @Log(title = "联系记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ContactLogBo bo) {
        return toAjax(contactLogService.insertByBo(bo));
    }

    /**
     * 修改联系记录
     */
    @SaCheckPermission("contact:log:edit")
    @Log(title = "联系记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ContactLogBo bo) {
        return toAjax(contactLogService.updateByBo(bo));
    }

    /**
     * 删除联系记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contact:log:remove")
    @Log(title = "联系记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(contactLogService.deleteWithValidByIds(List.of(ids), true));
    }
}
