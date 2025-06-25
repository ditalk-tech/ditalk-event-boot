package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.handler.IEventMemberHandler;
import org.dromara.module.event.domain.bo.EventMemberBo;
import org.dromara.module.event.domain.vo.EventMemberVo;
import org.dromara.module.event.service.IEventMemberService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final IEventMemberService eventMemberService;

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

    /**
     * 查看我的报名数据
     *
     * @param eventId 活动ID
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/my/{eventId}")
    public R<EventMemberVo> getMyEvent(@NotNull(message = "主键不能为空")
                                       @PathVariable Long eventId) {
        EventMemberBo bo = new EventMemberBo();
        bo.setEventId(eventId);
        bo.setMemberId(LoginHelper.getUserId());
        bo.setState(CommonConstants.AVAILABLE);
        List<EventMemberVo> eventMemberVos = eventMemberService.queryList(bo);
        if (eventMemberVos.size() == 0) {
            throw new UserException("无报名记录");
        } else if (eventMemberVos.size() == 1) {
            return R.ok(eventMemberVos.get(0));
        } else {
            throw new UserException("报名数据异常，请联系管理员");
        }
    }

}
