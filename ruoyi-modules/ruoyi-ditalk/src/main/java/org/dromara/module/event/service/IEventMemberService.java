package org.dromara.module.event.service;

import org.dromara.module.event.domain.vo.EventMemberVo;
import org.dromara.module.event.domain.bo.EventMemberBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.IdPageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 活动报名人Service接口
 *
 * @author weidixian
 * @date 2025-06-23
 */
public interface IEventMemberService {

    /**
     * 查询活动报名人
     *
     * @param id 主键
     * @return 活动报名人
     */
    EventMemberVo queryById(Long id);

    /**
     * 分页查询活动报名人列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动报名人分页列表
     */
    TableDataInfo<EventMemberVo> queryPageList(EventMemberBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的活动报名人列表
     *
     * @param bo 查询条件
     * @return 活动报名人列表
     */
    List<EventMemberVo> queryList(EventMemberBo bo);

    /**
     * 新增活动报名人
     *
     * @param bo 活动报名人
     * @return 是否新增成功
     */
    Boolean insertByBo(EventMemberBo bo);

    /**
     * 修改活动报名人
     *
     * @param bo 活动报名人
     * @return 是否修改成功
     */
    Boolean updateByBo(EventMemberBo bo);

    /**
     * 删除活动报名人
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除活动报名人信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询活动报名人列表
     *
     * @param bo 查询条件
     * @return 活动报名人列表
     */
    List<EventMemberVo> queryList(EventMemberBo bo, IdPageQuery pageQuery);
}
