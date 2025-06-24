package org.dromara.module.event.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.event.domain.EventInfo;
import org.dromara.module.event.domain.bo.EventInfoBo;
import org.dromara.module.event.domain.bo.EventInfoMemberBo;
import org.dromara.module.event.domain.vo.EventInfoVo;
import org.dromara.module.event.mapper.EventInfoMapper;
import org.dromara.module.event.service.IEventInfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 活动信息Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-22
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EventInfoServiceImpl implements IEventInfoService {

    private final EventInfoMapper baseMapper;

    /**
     * 查询活动信息
     *
     * @param id 主键
     * @return 活动信息
     */
    @Override
    @Cacheable(cacheNames = CacheNames.EventInfo, key = "#id")
    public EventInfoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询活动信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动信息分页列表
     */
    @Override
    public TableDataInfo<EventInfoVo> queryPageList(EventInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EventInfo> lqw = buildQueryWrapper(bo);
        Page<EventInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的活动信息列表
     *
     * @param bo 查询条件
     * @return 活动信息列表
     */
    @Override
    public List<EventInfoVo> queryList(EventInfoBo bo) {
        LambdaQueryWrapper<EventInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EventInfo> buildQueryWrapper(EventInfoBo bo) {
        LambdaQueryWrapper<EventInfo> lqw = buildWrapper(bo);
        lqw.eq(bo.getId() != null, EventInfo::getId, bo.getId());
        return lqw;
    }

    private LambdaQueryWrapper<EventInfo> buildWrapper(EventInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EventInfo> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(EventInfo::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            EventInfo::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), EventInfo::getTitle, bo.getTitle());
        lqw.between(params.get("beginPublishTime") != null && params.get("endPublishTime") != null,
            EventInfo::getPublishTime, params.get("beginPublishTime"), params.get("endPublishTime"));
        lqw.between(params.get("beginApplicationDeadline") != null && params.get("endApplicationDeadline") != null,
            EventInfo::getApplicationDeadline, params.get("beginApplicationDeadline"), params.get("endApplicationDeadline"));
        lqw.between(params.get("beginStartTime") != null && params.get("endStartTime") != null,
            EventInfo::getStartTime, params.get("beginStartTime"), params.get("endStartTime"));
        lqw.like(StringUtils.isNotBlank(bo.getLocation()), EventInfo::getLocation, bo.getLocation());
        lqw.eq(StringUtils.isNotBlank(bo.getState()), EventInfo::getState, bo.getState());
        lqw.ge(params.containsKey("getNewList"), EventInfo::getApplicationDeadline, new Date()); // 截止时间>=当前时间，取新数据
        lqw.lt(params.containsKey("getOldList"), EventInfo::getApplicationDeadline, new Date()); // 截止时间<当前时间，取旧数据
        return lqw;
    }

    /**
     * 新增活动信息
     *
     * @param bo 活动信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EventInfoBo bo) {
        EventInfo add = MapstructUtils.convert(bo, EventInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改活动信息
     *
     * @param bo 活动信息
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.EventInfo, key = "#bo.id")
    public Boolean updateByBo(EventInfoBo bo) {
        EventInfo update = MapstructUtils.convert(bo, EventInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EventInfo entity) {
        if (StringUtils.isBlank(entity.getMembers())) {
            entity.setMembers("[]");
        } else {
            try {
                JsonUtils.parseArray(entity.getMembers(), EventInfoMemberBo.class); // TODO 充分校验格式
            } catch (Exception e) {
                throw new UserException("成员信息格式错误!");
            }
        }
    }

    /**
     * 删除活动信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.EventInfo, key = "#id")
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 校验并批量删除活动信息信息
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
     * 通过ID分页查询活动信息列表
     *
     * @param bo 查询条件，params中存在"getNewList"取新数据，存在"getOldList"取旧数据
     * @return 活动信息列表
     */
    @Override
    public List<EventInfoVo> queryList(EventInfoBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<EventInfo> lqw = buildWrapper(bo);
        lqw.lt(pageQuery.getId() != null, EventInfo::getId, pageQuery.getId());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

    @Override
    public List<EventInfoVo> queryListByIds(List<Long> ids) {
        return baseMapper.selectVoByIds(ids);
    }

}
