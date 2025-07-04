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
import org.dromara.module.contact.domain.ContactLog;
import org.dromara.module.contact.domain.bo.ContactLogBo;
import org.dromara.module.contact.domain.vo.ContactLogVo;
import org.dromara.module.contact.mapper.ContactLogMapper;
import org.dromara.module.contact.service.IContactLogService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 联系记录Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContactLogServiceImpl implements IContactLogService {

    private final ContactLogMapper baseMapper;

    /**
     * 查询联系记录
     *
     * @param id 主键
     * @return 联系记录
     */
    @Override
    @Cacheable(cacheNames = CacheNames.ContactLog, key = "#id")
    public ContactLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询联系记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 联系记录分页列表
     */
    @Override
    public TableDataInfo<ContactLogVo> queryPageList(ContactLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ContactLog> lqw = buildQueryWrapper(bo);
        Page<ContactLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的联系记录列表
     *
     * @param bo 查询条件
     * @return 联系记录列表
     */
    @Override
    public List<ContactLogVo> queryList(ContactLogBo bo) {
        LambdaQueryWrapper<ContactLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ContactLog> buildQueryWrapper(ContactLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ContactLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, ContactLog::getId, bo.getId());
        lqw.orderByDesc(ContactLog::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ContactLog::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactLog::getState, bo.getState());
        lqw.eq(bo.getContactId() != null, ContactLog::getContactId, bo.getContactId());
        lqw.eq(bo.getOperatorId() != null, ContactLog::getOperatorId, bo.getOperatorId());
        lqw.eq(StringUtils.isNotBlank(bo.getChannel()), ContactLog::getChannel, bo.getChannel());
        lqw.eq(StringUtils.isNotBlank(bo.getSubject()), ContactLog::getSubject, bo.getSubject());
        return lqw;
    }

    /**
     * 新增联系记录
     *
     * @param bo 联系记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ContactLogBo bo) {
        ContactLog add = MapstructUtils.convert(bo, ContactLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改联系记录
     *
     * @param bo 联系记录
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.ContactLog, key = "#bo.id")
    public Boolean updateByBo(ContactLogBo bo) {
        ContactLog update = MapstructUtils.convert(bo, ContactLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ContactLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.ContactLog, key = "#id")
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
    public List<ContactLogVo> queryList(ContactLogBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<ContactLog> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, ContactLog::getId, pageQuery.getId());
        lqw.orderByDesc(ContactLog::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactLog::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }
}
