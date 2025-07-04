package org.dromara.module.news.service.impl;

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
import org.dromara.module.news.domain.NewsInfo;
import org.dromara.module.news.domain.bo.NewsInfoBo;
import org.dromara.module.news.domain.vo.NewsInfoVo;
import org.dromara.module.news.mapper.NewsInfoMapper;
import org.dromara.module.news.service.INewsInfoService;
import org.dromara.uni.controller.domain.bo.NewsInfoIdQueryBo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 喜讯新闻Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewsInfoServiceImpl implements INewsInfoService {

    private final NewsInfoMapper baseMapper;

    /**
     * 查询喜讯新闻
     *
     * @param id 主键
     * @return 喜讯新闻
     */
    @Override
    @Cacheable(cacheNames = CacheNames.NewsInfo, key = "#id")
    public NewsInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询喜讯新闻列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 喜讯新闻分页列表
     */
    @Override
    public TableDataInfo<NewsInfoVo> queryPageList(NewsInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NewsInfo> lqw = buildQueryWrapper(bo);
        Page<NewsInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的喜讯新闻列表
     *
     * @param bo 查询条件
     * @return 喜讯新闻列表
     */
    @Override
    public List<NewsInfoVo> queryList(NewsInfoBo bo) {
        LambdaQueryWrapper<NewsInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NewsInfo> buildQueryWrapper(NewsInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NewsInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, NewsInfo::getId, bo.getId());
        lqw.orderByDesc(NewsInfo::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            NewsInfo::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), NewsInfo::getState, bo.getState());
        lqw.eq(bo.getEventTime() != null, NewsInfo::getEventTime, bo.getEventTime());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), NewsInfo::getContent, bo.getContent());
        return lqw;
    }

    /**
     * 新增喜讯新闻
     *
     * @param bo 喜讯新闻
     * @return 是否新增成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.NewsInfo_Total, allEntries = true)
    public Boolean insertByBo(NewsInfoBo bo) {
        NewsInfo add = MapstructUtils.convert(bo, NewsInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改喜讯新闻
     *
     * @param bo 喜讯新闻
     * @return 是否修改成功
     */
    @Override
    @Caching(evict = {
        @CacheEvict(cacheNames = CacheNames.NewsInfo, key = "#bo.id"),
        @CacheEvict(cacheNames = CacheNames.NewsInfo_Total, allEntries = true)
    })
    public Boolean updateByBo(NewsInfoBo bo) {
        NewsInfo update = MapstructUtils.convert(bo, NewsInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(NewsInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @Caching(evict = {
        @CacheEvict(cacheNames = CacheNames.NewsInfo, key = "#id"),
        @CacheEvict(cacheNames = CacheNames.NewsInfo_Total, allEntries = true)
    })
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 校验并批量删除配置信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    @DSTransactional
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        Boolean flag = true;
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        for(Long id : ids) {
            flag = flag && SpringUtils.getAopProxy(this).deleteById(id);
        }
        return flag;
    }

    /**
     * 通过ID分页查询配置信息列表
     *
     * @param bo 查询条件
     * @return 配置信息列表
     */
    @Override
    public List<NewsInfoVo> queryList(NewsInfoIdQueryBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<NewsInfo> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, NewsInfo::getId, pageQuery.getId());
        lqw.orderByDesc(NewsInfo::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), NewsInfo::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

    @Override
    @Cacheable(cacheNames = CacheNames.NewsInfo_Total, key = "'total'")
    public Integer getTotal() {
        LambdaQueryWrapper<NewsInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(NewsInfo::getState, CommonConstants.AVAILABLE);
        return baseMapper.selectCount(lqw).intValue();
    }

}
