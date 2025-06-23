package org.dromara.module.event.service;

import org.dromara.module.event.domain.vo.EventMomentVo;
import org.dromara.module.event.domain.bo.EventMomentBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.IdPageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 活动瞬间Service接口
 *
 * @author weidixian
 * @date 2025-06-23
 */
public interface IEventMomentService {

    /**
     * 查询活动瞬间
     *
     * @param id 主键
     * @return 活动瞬间
     */
    EventMomentVo queryById(Long id);

    /**
     * 分页查询活动瞬间列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动瞬间分页列表
     */
    TableDataInfo<EventMomentVo> queryPageList(EventMomentBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的活动瞬间列表
     *
     * @param bo 查询条件
     * @return 活动瞬间列表
     */
    List<EventMomentVo> queryList(EventMomentBo bo);

    /**
     * 新增活动瞬间
     *
     * @param bo 活动瞬间
     * @return 是否新增成功
     */
    Boolean insertByBo(EventMomentBo bo);

    /**
     * 修改活动瞬间
     *
     * @param bo 活动瞬间
     * @return 是否修改成功
     */
    Boolean updateByBo(EventMomentBo bo);

    /**
     * 删除活动瞬间
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除活动瞬间信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询活动瞬间列表
     *
     * @param bo 查询条件
     * @return 活动瞬间列表
     */
    List<EventMomentVo> queryList(EventMomentBo bo, IdPageQuery pageQuery);

    /**
     * 通过活动ID查询活动瞬间列表
     *
     * @param eventId 活动ID
     * @return 活动瞬间列表
     */
    List<EventMomentVo> queryByEventId(Long eventId);
}
