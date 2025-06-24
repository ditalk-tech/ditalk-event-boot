package org.dromara.handler;

import jakarta.validation.constraints.NotNull;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.module.event.domain.vo.EventInfoVo;

import java.util.List;

/**
 * 活动信息应用接口
 *
 * @author weidixian
 */
public interface IEventInfoHandler {
    /**
     * 获取活动信息详情
     *
     * @param id 主键
     * @return 活动信息详情
     */
    EventInfoVo getDetail(@NotNull(message = "主键不能为空") Long id);

    /**
     * 获取我报名的活动信息列表
     *
     * @param pageQuery 分页参数
     * @return 活动信息列表
     */
    List<EventInfoVo> queryMyNewEvents(IdPageQuery pageQuery);

    /**
     * 获取我已结束的活动信息列表
     *
     * @param pageQuery 分页参数
     * @return 活动信息列表
     */
    List<EventInfoVo> queryMyOldEvents(IdPageQuery pageQuery);
}
