package org.dromara.module.contact.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.contact.domain.ContactTags;
import org.dromara.module.contact.domain.bo.ContactTagsBo;
import org.dromara.module.contact.domain.vo.ContactTagsVo;
import org.dromara.module.contact.mapper.ContactTagsMapper;
import org.dromara.module.contact.service.IContactTagsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 联系人标签Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContactTagsServiceImpl implements IContactTagsService {

    private final ContactTagsMapper baseMapper;

    /**
     * 查询联系人标签
     *
     * @param id 主键
     * @return 联系人标签
     */
    @Override
    public ContactTagsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询联系人标签列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 联系人标签分页列表
     */
    @Override
    public TableDataInfo<ContactTagsVo> queryPageList(ContactTagsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ContactTags> lqw = buildQueryWrapper(bo);
        Page<ContactTagsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的联系人标签列表
     *
     * @param bo 查询条件
     * @return 联系人标签列表
     */
    @Override
    public List<ContactTagsVo> queryList(ContactTagsBo bo) {
        LambdaQueryWrapper<ContactTags> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ContactTags> buildQueryWrapper(ContactTagsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ContactTags> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, ContactTags::getId, bo.getId());
        lqw.orderByDesc(ContactTags::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ContactTags::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactTags::getState, bo.getState());
        lqw.eq(bo.getContactId() != null, ContactTags::getContactId, bo.getContactId());
        lqw.eq(StringUtils.isNotBlank(bo.getCategory()), ContactTags::getCategory, bo.getCategory());
        lqw.eq(StringUtils.isNotBlank(bo.getName()), ContactTags::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增联系人标签
     *
     * @param bo 联系人标签
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ContactTagsBo bo) {
        ContactTags add = MapstructUtils.convert(bo, ContactTags.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改联系人标签
     *
     * @param bo 联系人标签
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ContactTagsBo bo) {
        ContactTags update = MapstructUtils.convert(bo, ContactTags.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ContactTags entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
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
    public List<ContactTagsVo> queryList(ContactTagsBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<ContactTags> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, ContactTags::getId, pageQuery.getId());
        lqw.orderByDesc(ContactTags::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactTags::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }
}
