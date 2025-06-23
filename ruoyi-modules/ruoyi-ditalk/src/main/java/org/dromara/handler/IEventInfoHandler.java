package org.dromara.handler;

import jakarta.validation.constraints.NotNull;
import org.dromara.module.event.domain.vo.EventInfoVo;

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
}
