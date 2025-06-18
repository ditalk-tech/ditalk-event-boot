package org.dromara.module.config.controller;

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
import org.dromara.module.config.domain.vo.UniAuthConfigVo;
import org.dromara.module.config.domain.bo.UniAuthConfigBo;
import org.dromara.module.config.service.IUniAuthConfigService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 小程序变量
 *
 * @author weidixian
 * @date 2025-06-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/config/authConfig")
public class UniAuthConfigController extends BaseController {

    private final IUniAuthConfigService uniAuthConfigService;

    /**
     * 查询小程序变量列表
     */
    @SaCheckPermission("config:authConfig:list")
    @GetMapping("/list")
    public TableDataInfo<UniAuthConfigVo> list(UniAuthConfigBo bo, PageQuery pageQuery) {
        return uniAuthConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出小程序变量列表
     */
    @SaCheckPermission("config:authConfig:export")
    @Log(title = "小程序变量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(UniAuthConfigBo bo, HttpServletResponse response) {
        List<UniAuthConfigVo> list = uniAuthConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "小程序变量", UniAuthConfigVo.class, response);
    }

    /**
     * 获取小程序变量详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("config:authConfig:query")
    @GetMapping("/{id}")
    public R<UniAuthConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(uniAuthConfigService.queryById(id));
    }

    /**
     * 新增小程序变量
     */
    @SaCheckPermission("config:authConfig:add")
    @Log(title = "小程序变量", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody UniAuthConfigBo bo) {
        return toAjax(uniAuthConfigService.insertByBo(bo));
    }

    /**
     * 修改小程序变量
     */
    @SaCheckPermission("config:authConfig:edit")
    @Log(title = "小程序变量", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody UniAuthConfigBo bo) {
        return toAjax(uniAuthConfigService.updateByBo(bo));
    }

    /**
     * 删除小程序变量
     *
     * @param ids 主键串
     */
    @SaCheckPermission("config:authConfig:remove")
    @Log(title = "小程序变量", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(uniAuthConfigService.deleteWithValidByIds(List.of(ids), true));
    }
}
