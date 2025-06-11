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
import org.dromara.module.contact.domain.vo.ContactCommonTagsVo;
import org.dromara.module.contact.domain.bo.ContactCommonTagsBo;
import org.dromara.module.contact.service.IContactCommonTagsService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 常用标签
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contact/commonTags")
public class ContactCommonTagsController extends BaseController {

    private final IContactCommonTagsService contactCommonTagsService;

    /**
     * 查询常用标签列表
     */
    @SaCheckPermission("contact:commonTags:list")
    @GetMapping("/list")
    public TableDataInfo<ContactCommonTagsVo> list(ContactCommonTagsBo bo, PageQuery pageQuery) {
        return contactCommonTagsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出常用标签列表
     */
    @SaCheckPermission("contact:commonTags:export")
    @Log(title = "常用标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ContactCommonTagsBo bo, HttpServletResponse response) {
        List<ContactCommonTagsVo> list = contactCommonTagsService.queryList(bo);
        ExcelUtil.exportExcel(list, "常用标签", ContactCommonTagsVo.class, response);
    }

    /**
     * 获取常用标签详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contact:commonTags:query")
    @GetMapping("/{id}")
    public R<ContactCommonTagsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(contactCommonTagsService.queryById(id));
    }

    /**
     * 新增常用标签
     */
    @SaCheckPermission("contact:commonTags:add")
    @Log(title = "常用标签", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ContactCommonTagsBo bo) {
        return toAjax(contactCommonTagsService.insertByBo(bo));
    }

    /**
     * 修改常用标签
     */
    @SaCheckPermission("contact:commonTags:edit")
    @Log(title = "常用标签", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ContactCommonTagsBo bo) {
        return toAjax(contactCommonTagsService.updateByBo(bo));
    }

    /**
     * 删除常用标签
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contact:commonTags:remove")
    @Log(title = "常用标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(contactCommonTagsService.deleteWithValidByIds(List.of(ids), true));
    }
}
