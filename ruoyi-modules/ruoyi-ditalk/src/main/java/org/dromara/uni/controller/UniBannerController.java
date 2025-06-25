package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.ConfigInfoCodeConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.web.core.BaseController;
import org.dromara.handler.IBannerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页横幅
 *
 * @author weidixian
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/banner")
public class UniBannerController extends BaseController {

    private final IBannerHandler bannerHandler;

    /**
     *
     */
    @GetMapping("/getImageUrl")
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    public R<String> getImageUrl() {
        return R.ok("SUCCESS", bannerHandler.getImageUrl(ConfigInfoCodeConstants.BannerImageCode));
    }

}
