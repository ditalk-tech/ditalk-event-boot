package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.handler.IEventMemberHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动名单
 *
 * @author weidixian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/event/member")
public class UniEventMemberController extends BaseController {

    private final IEventMemberHandler eventMemberHandler;

    /**
     * 报名活动
     *
     * @param eventId 活动ID
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "活动名单", businessType = BusinessType.INSERT)
    @PutMapping("/signup/{eventId}")
    @RepeatSubmit
    public R<Void> signup(@NotNull(message = "主键不能为空")
                          @PathVariable Long eventId) {
        Boolean signup = eventMemberHandler.signup(eventId, LoginHelper.getUserId());
        if (!signup) {
            return R.fail("报名失败");
        } else {
            return R.ok("报名成功");
        }
    }

}
