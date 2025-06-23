package org.dromara.module.event.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.module.event.domain.EventMoment;
import org.dromara.module.event.domain.bo.EventMomentBo;
import org.dromara.module.event.domain.vo.EventMomentVo;
import org.dromara.module.event.mapper.EventMomentMapper;
import org.dromara.module.event.service.IEventMomentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 活动瞬间Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EventMomentServiceImpl implements IEventMomentService {

    private final EventMomentMapper baseMapper;

    /**
     * 查询活动瞬间
     *
     * @param id 主键
     * @return 活动瞬间
     */
    @Override
    @Cacheable(cacheNames = CacheNames.EventMoment, key = "#id")
    public EventMomentVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询活动瞬间列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动瞬间分页列表
     */
    @Override
    public TableDataInfo<EventMomentVo> queryPageList(EventMomentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EventMoment> lqw = buildQueryWrapper(bo);
        Page<EventMomentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的活动瞬间列表
     *
     * @param bo 查询条件
     * @return 活动瞬间列表
     */
    @Override
    public List<EventMomentVo> queryList(EventMomentBo bo) {
        LambdaQueryWrapper<EventMoment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EventMoment> buildQueryWrapper(EventMomentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EventMoment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, EventMoment::getId, bo.getId());
        lqw.orderByDesc(EventMoment::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            EventMoment::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(bo.getEventId() != null, EventMoment::getEventId, bo.getEventId());
        lqw.eq(StringUtils.isNotBlank(bo.getState()), EventMoment::getState, bo.getState());
        return lqw;
    }

    /**
     * 新增活动瞬间
     *
     * @param bo 活动瞬间
     * @return 是否新增成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.EventMoment_EventId, key = "#bo.eventId")
    public Boolean insertByBo(EventMomentBo bo) {
        EventMoment add = MapstructUtils.convert(bo, EventMoment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改活动瞬间
     *
     * @param bo 活动瞬间
     * @return 是否修改成功
     */
    @Override
    @Caching(evict = {
        @CacheEvict(cacheNames = CacheNames.EventMoment_EventId, key = "#bo.eventId"),
        @CacheEvict(cacheNames = CacheNames.EventMoment, key = "#bo.id")
    })
    public Boolean updateByBo(EventMomentBo bo) {
        EventMoment update = MapstructUtils.convert(bo, EventMoment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EventMoment entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除活动瞬间
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.EventMoment, key = "#id")
    public Boolean deleteById(Long id) {
        EventMomentVo eventMomentVo = SpringUtils.getAopProxy(this).queryById(id);
        if (eventMomentVo != null) {
            CacheUtils.evict(CacheNames.EventMoment_EventId, eventMomentVo.getEventId());
        }
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 校验并批量删除活动瞬间信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    @DSTransactional
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        Boolean flag = true;
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        for (Long id : ids) {
            flag = flag && SpringUtils.getAopProxy(this).deleteById(id);
        }
        return flag;
    }

    /**
     * 通过ID分页查询活动瞬间列表
     *
     * @param bo 查询条件
     * @return 活动瞬间列表
     */
    @Override
    public List<EventMomentVo> queryList(EventMomentBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<EventMoment> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, EventMoment::getId, pageQuery.getId());
        lqw.orderByDesc(EventMoment::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), EventMoment::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

    @Override
    @Cacheable(cacheNames = CacheNames.EventMoment_EventId, key = "#eventId")
    public List<EventMomentVo> queryByEventId(Long eventId) {
        return baseMapper.selectVoList(new LambdaQueryWrapper<EventMoment>()
            .eq(EventMoment::getEventId, eventId)
            .eq(EventMoment::getState, CommonConstants.AVAILABLE)
        );
    }

}
