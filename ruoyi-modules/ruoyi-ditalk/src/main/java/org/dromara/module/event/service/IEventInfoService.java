package org.dromara.module.event.service;

import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.domain.bo.EventInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.IdPageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 活动信息Service接口
 *
 * @author weidixian
 * @date 2025-06-22
 */
public interface IEventInfoService {

    /**
     * 查询活动信息
     *
     * @param id 主键
     * @return 活动信息
     */
    EventInfoVo queryById(Long id);

    /**
     * 分页查询活动信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动信息分页列表
     */
    TableDataInfo<EventInfoVo> queryPageList(EventInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的活动信息列表
     *
     * @param bo 查询条件
     * @return 活动信息列表
     */
    List<EventInfoVo> queryList(EventInfoBo bo);

    /**
     * 新增活动信息
     *
     * @param bo 活动信息
     * @return 是否新增成功
     */
    Boolean insertByBo(EventInfoBo bo);

    /**
     * 修改活动信息
     *
     * @param bo 活动信息
     * @return 是否修改成功
     */
    Boolean updateByBo(EventInfoBo bo);

    /**
     * 删除活动信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除活动信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询活动信息列表
     *
     * @param bo 查询条件
     * @return 活动信息列表
     */
    List<EventInfoVo> queryList(EventInfoBo bo, IdPageQuery pageQuery);

    List<EventInfoVo> queryListByIds(List<Long> ids);
}
