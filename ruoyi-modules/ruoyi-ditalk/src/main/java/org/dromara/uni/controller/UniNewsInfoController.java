package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.web.core.BaseController;
import org.dromara.module.news.domain.bo.NewsInfoBo;
import org.dromara.module.news.domain.vo.NewsInfoVo;
import org.dromara.module.news.service.INewsInfoService;
import org.dromara.uni.controller.domain.bo.NewsInfoIdQueryBo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 喜讯新闻
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/news/info")
public class UniNewsInfoController extends BaseController {

    private final INewsInfoService newsInfoService;

    /**
     * 查询喜讯新闻列表
     * @param bo 查询条件
     * @param pageQuery 分页参数
     * @return 喜讯新闻列表
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/list")
    public R<List<NewsInfoVo>> list(NewsInfoIdQueryBo bo, IdPageQuery pageQuery) {
        if (bo.getState() == null) {
            bo.setState(CommonConstants.AVAILABLE);
        }
        return R.ok(newsInfoService.queryList(bo, pageQuery));
    }

    /**
     * 获取喜讯新闻详细信息
     *
     * @param id 主键
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/{id}")
    public R<NewsInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(newsInfoService.queryById(id));
    }

    /**
     * 新增喜讯新闻
     *
     * @param bo 喜讯新闻
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "喜讯新闻", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NewsInfoBo bo) {
        return toAjax(newsInfoService.insertByBo(bo));
    }

    /**
     * 修改喜讯新闻
     *
     * @param bo 喜讯新闻
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
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
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "喜讯新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(newsInfoService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 获取喜讯新闻总数
     *
     * @return 喜讯新闻总数
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/total")
    public R<Integer> getTotal() {
        return R.ok(newsInfoService.getTotal());
    }
}
