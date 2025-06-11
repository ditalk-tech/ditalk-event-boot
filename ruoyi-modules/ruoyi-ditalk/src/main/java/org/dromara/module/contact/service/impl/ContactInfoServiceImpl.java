package org.dromara.module.contact.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.module.contact.domain.bo.ContactInfoBo;
import org.dromara.module.contact.domain.vo.ContactInfoVo;
import org.dromara.module.contact.domain.ContactInfo;
import org.dromara.module.contact.mapper.ContactInfoMapper;
import org.dromara.module.contact.service.IContactInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 联系人Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContactInfoServiceImpl implements IContactInfoService {

    private final ContactInfoMapper baseMapper;

    /**
     * 查询联系人
     *
     * @param id 主键
     * @return 联系人
     */
    @Override
    public ContactInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询联系人列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 联系人分页列表
     */
    @Override
    public TableDataInfo<ContactInfoVo> queryPageList(ContactInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ContactInfo> lqw = buildQueryWrapper(bo);
        Page<ContactInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的联系人列表
     *
     * @param bo 查询条件
     * @return 联系人列表
     */
    @Override
    public List<ContactInfoVo> queryList(ContactInfoBo bo) {
        LambdaQueryWrapper<ContactInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ContactInfo> buildQueryWrapper(ContactInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ContactInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, ContactInfo::getId, bo.getId());
        lqw.orderByDesc(ContactInfo::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ContactInfo::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ContactInfo::getState, bo.getState());
        lqw.like(StringUtils.isNotBlank(bo.getName()), ContactInfo::getName, bo.getName());
        lqw.like(StringUtils.isNotBlank(bo.getNickname()), ContactInfo::getNickname, bo.getNickname());
        lqw.like(StringUtils.isNotBlank(bo.getPinyin()), ContactInfo::getPinyin, bo.getPinyin());
        lqw.like(StringUtils.isNotBlank(bo.getMobile()), ContactInfo::getMobile, bo.getMobile());
        lqw.like(StringUtils.isNotBlank(bo.getEmail()), ContactInfo::getEmail, bo.getEmail());
        lqw.eq(StringUtils.isNotBlank(bo.getGender()), ContactInfo::getGender, bo.getGender());
        lqw.between(params.get("beginBirthday") != null && params.get("endBirthday") != null,
            ContactInfo::getBirthday ,params.get("beginBirthday"), params.get("endBirthday"));
        lqw.like(StringUtils.isNotBlank(bo.getPlaceOfOrigin()), ContactInfo::getPlaceOfOrigin, bo.getPlaceOfOrigin());
        lqw.like(StringUtils.isNotBlank(bo.getAddress()), ContactInfo::getAddress, bo.getAddress());
        lqw.like(StringUtils.isNotBlank(bo.getGraduationSchool()), ContactInfo::getGraduationSchool, bo.getGraduationSchool());
        lqw.like(StringUtils.isNotBlank(bo.getQualification()), ContactInfo::getQualification, bo.getQualification());
        lqw.like(StringUtils.isNotBlank(bo.getOrganization()), ContactInfo::getOrganization, bo.getOrganization());
        lqw.like(StringUtils.isNotBlank(bo.getPosition()), ContactInfo::getPosition, bo.getPosition());
        lqw.like(StringUtils.isNotBlank(bo.getSocialRole()), ContactInfo::getSocialRole, bo.getSocialRole());
        lqw.between(params.get("beginLastInteractionTime") != null && params.get("endLastInteractionTime") != null,
            ContactInfo::getLastInteractionTime ,params.get("beginLastInteractionTime"), params.get("endLastInteractionTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getInteractionFrequency()), ContactInfo::getInteractionFrequency, bo.getInteractionFrequency());
        return lqw;
    }

    /**
     * 新增联系人
     *
     * @param bo 联系人
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ContactInfoBo bo) {
        ContactInfo add = MapstructUtils.convert(bo, ContactInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改联系人
     *
     * @param bo 联系人
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ContactInfoBo bo) {
        ContactInfo update = MapstructUtils.convert(bo, ContactInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ContactInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除联系人信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
