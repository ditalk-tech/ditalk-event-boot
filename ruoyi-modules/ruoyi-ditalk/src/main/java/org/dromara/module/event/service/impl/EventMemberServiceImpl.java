package org.dromara.module.event.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.event.domain.EventMember;
import org.dromara.module.event.domain.bo.EventMemberBo;
import org.dromara.module.event.domain.vo.EventMemberVo;
import org.dromara.module.event.mapper.EventMemberMapper;
import org.dromara.module.event.service.IEventMemberService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 活动报名人Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EventMemberServiceImpl implements IEventMemberService {

    private final EventMemberMapper baseMapper;

    /**
     * 查询活动报名人
     *
     * @param id 主键
     * @return 活动报名人
     */
    @Override
    @Cacheable(cacheNames = CacheNames.EventMember, key = "#id")
    public EventMemberVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询活动报名人列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动报名人分页列表
     */
    @Override
    public TableDataInfo<EventMemberVo> queryPageList(EventMemberBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EventMember> lqw = buildQueryWrapper(bo);
        Page<EventMemberVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的活动报名人列表
     *
     * @param bo 查询条件
     * @return 活动报名人列表
     */
    @Override
    public List<EventMemberVo> queryList(EventMemberBo bo) {
        LambdaQueryWrapper<EventMember> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EventMember> buildQueryWrapper(EventMemberBo bo) {
        LambdaQueryWrapper<EventMember> lqw = buildWrapper(bo);
        lqw.eq(bo.getId() != null, EventMember::getId, bo.getId());
        return lqw;
    }

    private LambdaQueryWrapper<EventMember> buildWrapper(EventMemberBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EventMember> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(EventMember::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            EventMember::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(bo.getEventId() != null, EventMember::getEventId, bo.getEventId());
        lqw.eq(bo.getMemberId() != null, EventMember::getMemberId, bo.getMemberId());
        lqw.ge(params.get("geStartTime") != null, EventMember::getStartTime, params.get("geStartTime"));
        lqw.lt(params.get("ltStartTime") != null, EventMember::getStartTime, params.get("ltStartTime"));
        lqw.ge(bo.getSignCode() != null, EventMember::getSignCode, bo.getSignCode());
        lqw.eq(StringUtils.isNotBlank(bo.getState()), EventMember::getState, bo.getState());
        return lqw;
    }

    /**
     * 新增活动报名人
     *
     * @param bo 活动报名人
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EventMemberBo bo) {
        EventMember add = MapstructUtils.convert(bo, EventMember.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改活动报名人
     *
     * @param bo 活动报名人
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.EventMember, key = "#bo.id")
    public Boolean updateByBo(EventMemberBo bo) {
        EventMember update = MapstructUtils.convert(bo, EventMember.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EventMember entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除活动报名人
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.EventMember, key = "#id")
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 校验并批量删除活动报名人信息
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
     * 通过ID分页查询活动报名人列表
     *
     * @param bo 查询条件
     * @return 活动报名人列表
     */
    @Override
    public List<EventMemberVo> queryList(EventMemberBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<EventMember> lqw = buildWrapper(bo);
        lqw.lt(pageQuery.getId() != null, EventMember::getId, pageQuery.getId());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

}
