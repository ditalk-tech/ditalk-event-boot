package org.dromara.module.member.controller;

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
import org.dromara.module.member.domain.vo.MemberPhotoVo;
import org.dromara.module.member.domain.bo.MemberPhotoBo;
import org.dromara.module.member.service.IMemberPhotoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 会员照片
 *
 * @author weidixian
 * @date 2025-06-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/photo")
public class MemberPhotoController extends BaseController {

    private final IMemberPhotoService memberPhotoService;

    /**
     * 查询会员照片列表
     */
    @SaCheckPermission("member:photo:list")
    @GetMapping("/list")
    public TableDataInfo<MemberPhotoVo> list(MemberPhotoBo bo, PageQuery pageQuery) {
        return memberPhotoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员照片列表
     */
    @SaCheckPermission("member:photo:export")
    @Log(title = "会员照片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberPhotoBo bo, HttpServletResponse response) {
        List<MemberPhotoVo> list = memberPhotoService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员照片", MemberPhotoVo.class, response);
    }

    /**
     * 获取会员照片详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("member:photo:query")
    @GetMapping("/{id}")
    public R<MemberPhotoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(memberPhotoService.queryById(id));
    }

    /**
     * 新增会员照片
     */
    @SaCheckPermission("member:photo:add")
    @Log(title = "会员照片", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberPhotoBo bo) {
        return toAjax(memberPhotoService.insertByBo(bo));
    }

    /**
     * 修改会员照片
     */
    @SaCheckPermission("member:photo:edit")
    @Log(title = "会员照片", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberPhotoBo bo) {
        return toAjax(memberPhotoService.updateByBo(bo));
    }

    /**
     * 删除会员照片
     *
     * @param ids 主键串
     */
    @SaCheckPermission("member:photo:remove")
    @Log(title = "会员照片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(memberPhotoService.deleteWithValidByIds(List.of(ids), true));
    }
}
