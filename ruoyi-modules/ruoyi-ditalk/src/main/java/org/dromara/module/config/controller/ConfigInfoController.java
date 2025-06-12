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
import org.dromara.module.config.domain.vo.ConfigInfoVo;
import org.dromara.module.config.domain.bo.ConfigInfoBo;
import org.dromara.module.config.service.IConfigInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 配置信息
 *
 * @author weidixian
 * @date 2025-06-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/config/info")
public class ConfigInfoController extends BaseController {

    private final IConfigInfoService configInfoService;

    /**
     * 查询配置信息列表
     */
    @SaCheckPermission("config:info:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigInfoVo> list(ConfigInfoBo bo, PageQuery pageQuery) {
        return configInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出配置信息列表
     */
    @SaCheckPermission("config:info:export")
    @Log(title = "配置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ConfigInfoBo bo, HttpServletResponse response) {
        List<ConfigInfoVo> list = configInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "配置信息", ConfigInfoVo.class, response);
    }

    /**
     * 获取配置信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("config:info:query")
    @GetMapping("/{id}")
    public R<ConfigInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(configInfoService.queryById(id));
    }

    /**
     * 新增配置信息
     */
    @SaCheckPermission("config:info:add")
    @Log(title = "配置信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ConfigInfoBo bo) {
        return toAjax(configInfoService.insertByBo(bo));
    }

    /**
     * 修改配置信息
     */
    @SaCheckPermission("config:info:edit")
    @Log(title = "配置信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ConfigInfoBo bo) {
        return toAjax(configInfoService.updateByBo(bo));
    }

    /**
     * 删除配置信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("config:info:remove")
    @Log(title = "配置信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(configInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
