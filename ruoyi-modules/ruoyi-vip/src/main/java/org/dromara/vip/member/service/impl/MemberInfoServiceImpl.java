package org.dromara.vip.member.service.impl;

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
import org.dromara.vip.member.domain.bo.MemberInfoBo;
import org.dromara.vip.member.domain.vo.MemberInfoVo;
import org.dromara.vip.member.domain.MemberInfo;
import org.dromara.vip.member.mapper.MemberInfoMapper;
import org.dromara.vip.member.service.IMemberInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员信息Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberInfoServiceImpl implements IMemberInfoService {

    private final MemberInfoMapper baseMapper;

    /**
     * 查询会员信息
     *
     * @param id 主键
     * @return 会员信息
     */
    @Override
    public MemberInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询会员信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会员信息分页列表
     */
    @Override
    public TableDataInfo<MemberInfoVo> queryPageList(MemberInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberInfo> lqw = buildQueryWrapper(bo);
        Page<MemberInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会员信息列表
     *
     * @param bo 查询条件
     * @return 会员信息列表
     */
    @Override
    public List<MemberInfoVo> queryList(MemberInfoBo bo) {
        LambdaQueryWrapper<MemberInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberInfo> buildQueryWrapper(MemberInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, MemberInfo::getId, bo.getId());
        lqw.orderByDesc(MemberInfo::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            MemberInfo::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), MemberInfo::getState, bo.getState());
        lqw.eq(bo.getDeptId() != null, MemberInfo::getDeptId, bo.getDeptId());
        lqw.like(StringUtils.isNotBlank(bo.getUserName()), MemberInfo::getUserName, bo.getUserName());
        lqw.like(StringUtils.isNotBlank(bo.getNickName()), MemberInfo::getNickName, bo.getNickName());
        lqw.eq(StringUtils.isNotBlank(bo.getUserType()), MemberInfo::getUserType, bo.getUserType());
        lqw.eq(StringUtils.isNotBlank(bo.getEmail()), MemberInfo::getEmail, bo.getEmail());
        lqw.eq(StringUtils.isNotBlank(bo.getPhoneNumber()), MemberInfo::getPhoneNumber, bo.getPhoneNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getSex()), MemberInfo::getSex, bo.getSex());
        lqw.eq(StringUtils.isNotBlank(bo.getLoginIp()), MemberInfo::getLoginIp, bo.getLoginIp());
        lqw.between(params.get("beginLoginDate") != null && params.get("endLoginDate") != null,
            MemberInfo::getLoginDate ,params.get("beginLoginDate"), params.get("endLoginDate"));
        lqw.between(params.get("beginBirthday") != null && params.get("endBirthday") != null,
            MemberInfo::getBirthday ,params.get("beginBirthday"), params.get("endBirthday"));
        lqw.between(params.get("beginTall") != null && params.get("endTall") != null,
            MemberInfo::getTall ,params.get("beginTall"), params.get("endTall"));
        lqw.eq(StringUtils.isNotBlank(bo.getQualification()), MemberInfo::getQualification, bo.getQualification());
        lqw.eq(StringUtils.isNotBlank(bo.getCarrer()), MemberInfo::getCarrer, bo.getCarrer());
        lqw.eq(StringUtils.isNotBlank(bo.getPlaceOfOrigin()), MemberInfo::getPlaceOfOrigin, bo.getPlaceOfOrigin());
        lqw.like(StringUtils.isNotBlank(bo.getHobby()), MemberInfo::getHobby, bo.getHobby());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenState()), MemberInfo::getOpenState, bo.getOpenState());
        return lqw;
    }

    /**
     * 新增会员信息
     *
     * @param bo 会员信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(MemberInfoBo bo) {
        MemberInfo add = MapstructUtils.convert(bo, MemberInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会员信息
     *
     * @param bo 会员信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(MemberInfoBo bo) {
        MemberInfo update = MapstructUtils.convert(bo, MemberInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除会员信息信息
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
