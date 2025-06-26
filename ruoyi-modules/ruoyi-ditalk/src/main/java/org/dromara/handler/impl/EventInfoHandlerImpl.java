package org.dromara.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.utils.DateUtils;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.handler.IEventInfoHandler;
import org.dromara.module.event.domain.bo.EventMemberBo;
import org.dromara.module.event.domain.bo.EventMomentBo;
import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.domain.vo.EventMemberVo;
import org.dromara.module.event.domain.vo.EventMomentVo;
import org.dromara.module.event.service.IEventInfoService;
import org.dromara.module.event.service.IEventMemberService;
import org.dromara.module.event.service.IEventMomentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动信息应用接口
 *
 * @author weidixian
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EventInfoHandlerImpl implements IEventInfoHandler {

    private final IEventInfoService eventInfoService;
    private final IEventMemberService eventMemberService;
    private final IEventMomentService eventMomentService;

    @Override
    public EventInfoVo getDetail(Long id) {
        EventInfoVo eventInfoVo = eventInfoService.queryById(id);
        if (eventInfoVo != null) {
            EventMomentBo bo = new EventMomentBo();
            bo.setEventId(eventInfoVo.getId());
            List<EventMomentVo> eventMomentVos = eventMomentService.queryByEventId(eventInfoVo.getId());
            eventInfoVo.setEventMoments(eventMomentVos);
        }
        return eventInfoVo;
    }

    @Override
    public List<EventInfoVo> queryMyNewEvents(IdPageQuery pageQuery) {
        EventMemberBo eventMemberBo = new EventMemberBo();
        eventMemberBo.setMemberId(LoginHelper.getUserId());
        eventMemberBo.setState(CommonConstants.AVAILABLE);
        Map<String, Object> params = new HashMap<>();
        params.put("geStartTime", DateUtils.addHours(new Date(), -1));
        eventMemberBo.setParams(params);
        List<EventMemberVo> eventMemberVoList = eventMemberService.queryList(eventMemberBo, pageQuery);
        if (eventMemberVoList.isEmpty()) {
            return List.of();
        } else {
            // 获取活动ID
            List<Long> eventIds = eventMemberVoList.stream().map(EventMemberVo::getEventId).toList();
            // 返回活动信息集合
            return eventInfoService.queryListByIds(eventIds);
        }
    }

    @Override
    public List<EventInfoVo> queryMyOldEvents(IdPageQuery pageQuery) {
        EventMemberBo eventMemberBo = new EventMemberBo();
        eventMemberBo.setMemberId(LoginHelper.getUserId());
        eventMemberBo.setState(CommonConstants.AVAILABLE);
        Map<String, Object> params = new HashMap<>();
        params.put("ltStartTime", DateUtils.addHours(new Date(), -1));
        eventMemberBo.setParams(params);
        List<EventMemberVo> eventMemberVoList = eventMemberService.queryList(eventMemberBo, pageQuery);
        // 获取活动ID
        List<Long> eventIds = eventMemberVoList.stream().map(EventMemberVo::getEventId).toList();
        if (eventIds.isEmpty()) {
            return List.of();
        } else {
            // 返回活动信息集合
            return eventInfoService.queryListByIds(eventIds);
        }
    }
}
