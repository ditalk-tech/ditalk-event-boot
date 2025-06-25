package org.dromara.handler.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.lock.annotation.Lock4j;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.utils.CodeGeneratorUtil;
import org.dromara.handler.IEventMemberHandler;
import org.dromara.module.event.domain.bo.EventInfoBo;
import org.dromara.module.event.domain.bo.EventInfoMemberBo;
import org.dromara.module.event.domain.bo.EventMemberBo;
import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.domain.vo.EventMemberVo;
import org.dromara.module.event.service.IEventInfoService;
import org.dromara.module.event.service.IEventMemberService;
import org.dromara.module.member.domain.vo.MemberInfoVo;
import org.dromara.module.member.service.IMemberInfoService;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysOssService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动名单应用接口
 *
 * @author weidixian
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EventMemberHandlerImpl implements IEventMemberHandler {

    private final IMemberInfoService memberInfoService;
    private final IEventMemberService eventMemberService;
    private final IEventInfoService eventInfoService;
    private final ISysOssService sysOssService;

    @Override
    @DSTransactional
    @Lock4j(expire = 10000L)
    public Boolean signup(Long eventId, Long memberId) {
        // 查询活动名单
        EventMemberBo bo = new EventMemberBo();
        bo.setEventId(eventId);
        bo.setMemberId(memberId);
        List<EventMemberVo> eventMemberVos = eventMemberService.queryList(bo);
        // 判断是否已报名
        if (eventMemberVos.size() > 0) throw new UserException("已报名");
        // 获取活动信息
        EventInfoVo eventInfoVo = eventInfoService.queryById(eventId);
        if (eventInfoVo == null) throw new UserException("活动数据异常");
        // 获取会员信息
        MemberInfoVo memberInfoVo = memberInfoService.queryById(memberId);
        if (memberInfoVo == null) throw new UserException("用户数据异常");
        // 获取会员头像
        String avatarUrl = null;
        if (memberInfoVo.getAvatar() != null) {
            SysOssVo ossVo = sysOssService.getById(memberInfoVo.getAvatar());
            avatarUrl = ossVo.getUrl();
        }
        // 获取活动信息中活动名单快照信息
        List<EventInfoMemberBo> eventInfoMemberBos = JsonUtils.parseArray(eventInfoVo.getMembers(), EventInfoMemberBo.class);
        if (eventInfoMemberBos.size() >= eventInfoVo.getQuota()) throw new UserException("活动已满额!");
        // 组装快照活动名单
        EventInfoMemberBo eventInfoMemberBo = new EventInfoMemberBo();
        eventInfoMemberBo.setId(memberInfoVo.getId());
        eventInfoMemberBo.setName(memberInfoVo.getNickName());
        eventInfoMemberBo.setSex(memberInfoVo.getSex());
        eventInfoMemberBo.setAvatar(avatarUrl);
        // 更新活动信息中活动名单快照信息
        eventInfoMemberBos.add(eventInfoMemberBo);
        eventInfoVo.setMembers(JsonUtils.toJsonString(eventInfoMemberBos));
        EventInfoBo eventInfoBo = new EventInfoBo();
        eventInfoBo.setId(eventInfoVo.getId());
        eventInfoBo.setVersion(eventInfoVo.getVersion());
        eventInfoBo.setMembers(eventInfoVo.getMembers());
        eventInfoService.updateByBo(eventInfoBo);
        // 处理活动名单
        // 生成签到码
        int signCode = CodeGeneratorUtil.next4digit().intValue();
        EventMemberBo codeQueryBo = new EventMemberBo();
        codeQueryBo.setEventId(eventInfoVo.getId());
        codeQueryBo.setSignCode(signCode);
        List<EventMemberVo> queryList = eventMemberService.queryList(codeQueryBo);
        // 判断签到码是否重复，重新生成
        while (queryList.size() > 0) {
            signCode = CodeGeneratorUtil.next4digit().intValue();
            codeQueryBo.setSignCode(signCode);
            queryList = eventMemberService.queryList(codeQueryBo);
        }
        // 添加活动名单
        EventMemberBo eventMemberBo = new EventMemberBo();
        eventMemberBo.setEventId(eventInfoVo.getId());
        eventMemberBo.setMemberId(memberInfoVo.getId());
        eventMemberBo.setStartTime(eventInfoVo.getStartTime());
        eventMemberBo.setSignCode(signCode);
        eventMemberBo.setState(CommonConstants.AVAILABLE);
        return eventMemberService.insertByBo(eventMemberBo);
    }
}
