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
import org.dromara.vip.member.domain.vo.MemberOpenidVo;
import org.dromara.vip.member.domain.bo.MemberOpenidBo;
import org.dromara.vip.member.service.IMemberOpenidService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 会员OpenId
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/openid")
public class MemberOpenidController extends BaseController {

    private final IMemberOpenidService memberOpenidService;

    /**
     * 查询会员OpenId列表
     */
    @SaCheckPermission("member:openid:list")
    @GetMapping("/list")
    public TableDataInfo<MemberOpenidVo> list(MemberOpenidBo bo, PageQuery pageQuery) {
        return memberOpenidService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员OpenId列表
     */
    @SaCheckPermission("member:openid:export")
    @Log(title = "会员OpenId", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberOpenidBo bo, HttpServletResponse response) {
        List<MemberOpenidVo> list = memberOpenidService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员OpenId", MemberOpenidVo.class, response);
    }

    /**
     * 获取会员OpenId详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("member:openid:query")
    @GetMapping("/{id}")
    public R<MemberOpenidVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(memberOpenidService.queryById(id));
    }

    /**
     * 新增会员OpenId
     */
    @SaCheckPermission("member:openid:add")
    @Log(title = "会员OpenId", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberOpenidBo bo) {
        return toAjax(memberOpenidService.insertByBo(bo));
    }

    /**
     * 修改会员OpenId
     */
    @SaCheckPermission("member:openid:edit")
    @Log(title = "会员OpenId", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberOpenidBo bo) {
        return toAjax(memberOpenidService.updateByBo(bo));
    }

    /**
     * 删除会员OpenId
     *
     * @param ids 主键串
     */
    @SaCheckPermission("member:openid:remove")
    @Log(title = "会员OpenId", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(memberOpenidService.deleteWithValidByIds(List.of(ids), true));
    }
}
