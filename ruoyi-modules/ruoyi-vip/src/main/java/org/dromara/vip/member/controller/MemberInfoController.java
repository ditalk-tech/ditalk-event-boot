package org.dromara.vip.member.controller;

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
import org.dromara.vip.member.domain.vo.MemberInfoVo;
import org.dromara.vip.member.domain.bo.MemberInfoBo;
import org.dromara.vip.member.service.IMemberInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 会员信息
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/info")
public class MemberInfoController extends BaseController {

    private final IMemberInfoService memberInfoService;

    /**
     * 查询会员信息列表
     */
    @SaCheckPermission("member:info:list")
    @GetMapping("/list")
    public TableDataInfo<MemberInfoVo> list(MemberInfoBo bo, PageQuery pageQuery) {
        return memberInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员信息列表
     */
    @SaCheckPermission("member:info:export")
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberInfoBo bo, HttpServletResponse response) {
        List<MemberInfoVo> list = memberInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员信息", MemberInfoVo.class, response);
    }

    /**
     * 获取会员信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("member:info:query")
    @GetMapping("/{id}")
    public R<MemberInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(memberInfoService.queryById(id));
    }

    /**
     * 新增会员信息
     */
    @SaCheckPermission("member:info:add")
    @Log(title = "会员信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberInfoBo bo) {
        return toAjax(memberInfoService.insertByBo(bo));
    }

    /**
     * 修改会员信息
     */
    @SaCheckPermission("member:info:edit")
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberInfoBo bo) {
        return toAjax(memberInfoService.updateByBo(bo));
    }

    /**
     * 删除会员信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("member:info:remove")
    @Log(title = "会员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(memberInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
