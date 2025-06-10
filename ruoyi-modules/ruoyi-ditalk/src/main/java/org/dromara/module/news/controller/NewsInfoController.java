package org.dromara.module.news.controller;

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
import org.dromara.module.news.domain.vo.NewsInfoVo;
import org.dromara.module.news.domain.bo.NewsInfoBo;
import org.dromara.module.news.service.INewsInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 喜讯新闻
 *
 * @author weidixian
 * @date 2025-06-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/news/info")
public class NewsInfoController extends BaseController {

    private final INewsInfoService newsInfoService;

    /**
     * 查询喜讯新闻列表
     */
    @SaCheckPermission("news:info:list")
    @GetMapping("/list")
    public TableDataInfo<NewsInfoVo> list(NewsInfoBo bo, PageQuery pageQuery) {
        return newsInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出喜讯新闻列表
     */
    @SaCheckPermission("news:info:export")
    @Log(title = "喜讯新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(NewsInfoBo bo, HttpServletResponse response) {
        List<NewsInfoVo> list = newsInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "喜讯新闻", NewsInfoVo.class, response);
    }

    /**
     * 获取喜讯新闻详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("news:info:query")
    @GetMapping("/{id}")
    public R<NewsInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(newsInfoService.queryById(id));
    }

    /**
     * 新增喜讯新闻
     */
    @SaCheckPermission("news:info:add")
    @Log(title = "喜讯新闻", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NewsInfoBo bo) {
        return toAjax(newsInfoService.insertByBo(bo));
    }

    /**
     * 修改喜讯新闻
     */
    @SaCheckPermission("news:info:edit")
    @Log(title = "喜讯新闻", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NewsInfoBo bo) {
        return toAjax(newsInfoService.updateByBo(bo));
    }

    /**
     * 删除喜讯新闻
     *
     * @param ids 主键串
     */
    @SaCheckPermission("news:info:remove")
    @Log(title = "喜讯新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(newsInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
