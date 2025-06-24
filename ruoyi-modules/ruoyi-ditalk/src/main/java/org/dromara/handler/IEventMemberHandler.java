package org.dromara.handler;

/**
 * 活动名单应用接口
 *
 * @author weidixian
 */
public interface IEventMemberHandler {

    Boolean signup(Long eventId, Long memberId);
}
