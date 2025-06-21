package org.dromara.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dromara.common.constant.ConfigInfoCodeConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.web.core.BaseController;
import org.dromara.handler.IBannerHandler;
import org.dromara.module.config.domain.vo.ConfigInfoVo;
import org.dromara.module.config.service.IConfigInfoService;
import org.dromara.server.controller.domain.bo.BannerImageBo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 首页横幅
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/server/banner/")
public class BannerController extends BaseController {

    private final IConfigInfoService configInfoService;
    private final IBannerHandler bannerHandler;

    @SaCheckPermission("config:banner:query")
    @GetMapping()
    public R<ConfigInfoVo> getInfo() {
        return R.ok(configInfoService.queryOneByCode(ConfigInfoCodeConstants.BannerImageCode));
    }

    @SaCheckPermission("config:banner:edit")
    @Log(title = "首页横幅", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Valid @RequestBody BannerImageBo bo) {
        return toAjax(bannerHandler.edit(bo));
    }

}
