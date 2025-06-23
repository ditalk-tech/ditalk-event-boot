package org.dromara.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.handler.IEventInfoHandler;
import org.dromara.module.event.domain.bo.EventMomentBo;
import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.domain.vo.EventMomentVo;
import org.dromara.module.event.service.IEventInfoService;
import org.dromara.module.event.service.IEventMomentService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
