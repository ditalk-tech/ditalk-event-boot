package org.dromara.module.contact.service.impl;

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
import org.dromara.module.contact.domain.ContactCommonTags;
import org.dromara.module.contact.domain.bo.ContactCommonTagsBo;
import org.dromara.module.contact.domain.vo.ContactCommonTagsVo;
import org.dromara.module.contact.mapper.ContactCommonTagsMapper;
import org.dromara.module.contact.service.IContactCommonTagsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 常用标签Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContactCommonTagsServiceImpl implements IContactCommonTagsService {

    private final ContactCommonTagsMapper baseMapper;

    /**
     * 查询常用标签
     *
     * @param id 主键
     * @return 常用标签
     */
    @Override
    @Cacheable(cacheNames = CacheNames.ContactCommonTags, key = "#id")
    public ContactCommonTagsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询常用标签列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 常用标签分页列表
     */
    @Override
    public TableDataInfo<ContactCommonTagsVo> queryPageList(ContactCommonTagsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ContactCommonTags> lqw = buildQueryWrapper(bo);
        Page<ContactCommonTagsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的常用标签列表
     *
     * @param bo 查询条件
     * @return 常用标签列表
     */
    @Override
    public List<ContactCommonTagsVo> queryList(ContactCommonTagsBo bo) {
        LambdaQueryWrapper<ContactCommonTags> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ContactCommonTags> buildQueryWrapper(ContactCommonTagsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ContactCommonTags> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, ContactCommonTags::getId, bo.getId());
        lqw.orderByDesc(ContactCommonTags::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ContactCommonTags::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactCommonTags::getState, bo.getState());
        lqw.like(StringUtils.isNotBlank(bo.getCategory()), ContactCommonTags::getCategory, bo.getCategory());
        lqw.like(StringUtils.isNotBlank(bo.getName()), ContactCommonTags::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增常用标签
     *
     * @param bo 常用标签
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ContactCommonTagsBo bo) {
        ContactCommonTags add = MapstructUtils.convert(bo, ContactCommonTags.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改常用标签
     *
     * @param bo 常用标签
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.ContactCommonTags, key = "#bo.id")
    public Boolean updateByBo(ContactCommonTagsBo bo) {
        ContactCommonTags update = MapstructUtils.convert(bo, ContactCommonTags.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ContactCommonTags entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.ContactCommonTags, key = "#id")
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
    public List<ContactCommonTagsVo> queryList(ContactCommonTagsBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<ContactCommonTags> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, ContactCommonTags::getId, pageQuery.getId());
        lqw.orderByDesc(ContactCommonTags::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactCommonTags::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }
}
