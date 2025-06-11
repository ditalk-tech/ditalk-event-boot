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
import org.dromara.module.contact.domain.vo.ContactTagsVo;
import org.dromara.module.contact.domain.bo.ContactTagsBo;
import org.dromara.module.contact.service.IContactTagsService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 联系人标签
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contact/tags")
public class ContactTagsController extends BaseController {

    private final IContactTagsService contactTagsService;

    /**
     * 查询联系人标签列表
     */
    @SaCheckPermission("contact:tags:list")
    @GetMapping("/list")
    public TableDataInfo<ContactTagsVo> list(ContactTagsBo bo, PageQuery pageQuery) {
        return contactTagsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出联系人标签列表
     */
    @SaCheckPermission("contact:tags:export")
    @Log(title = "联系人标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ContactTagsBo bo, HttpServletResponse response) {
        List<ContactTagsVo> list = contactTagsService.queryList(bo);
        ExcelUtil.exportExcel(list, "联系人标签", ContactTagsVo.class, response);
    }

    /**
     * 获取联系人标签详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contact:tags:query")
    @GetMapping("/{id}")
    public R<ContactTagsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(contactTagsService.queryById(id));
    }

    /**
     * 新增联系人标签
     */
    @SaCheckPermission("contact:tags:add")
    @Log(title = "联系人标签", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ContactTagsBo bo) {
        return toAjax(contactTagsService.insertByBo(bo));
    }

    /**
     * 修改联系人标签
     */
    @SaCheckPermission("contact:tags:edit")
    @Log(title = "联系人标签", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ContactTagsBo bo) {
        return toAjax(contactTagsService.updateByBo(bo));
    }

    /**
     * 删除联系人标签
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contact:tags:remove")
    @Log(title = "联系人标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(contactTagsService.deleteWithValidByIds(List.of(ids), true));
    }
}
