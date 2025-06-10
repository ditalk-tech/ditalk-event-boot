package org.dromara.module.member.service.impl;

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
import org.dromara.module.member.domain.bo.MemberOpenidBo;
import org.dromara.module.member.domain.vo.MemberOpenidVo;
import org.dromara.module.member.domain.MemberOpenid;
import org.dromara.module.member.mapper.MemberOpenidMapper;
import org.dromara.module.member.service.IMemberOpenidService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员OpenIdService业务层处理
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberOpenidServiceImpl implements IMemberOpenidService {

    private final MemberOpenidMapper baseMapper;

    /**
     * 查询会员OpenId
     *
     * @param id 主键
     * @return 会员OpenId
     */
    @Override
    public MemberOpenidVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询会员OpenId列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会员OpenId分页列表
     */
    @Override
    public TableDataInfo<MemberOpenidVo> queryPageList(MemberOpenidBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberOpenid> lqw = buildQueryWrapper(bo);
        Page<MemberOpenidVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会员OpenId列表
     *
     * @param bo 查询条件
     * @return 会员OpenId列表
     */
    @Override
    public List<MemberOpenidVo> queryList(MemberOpenidBo bo) {
        LambdaQueryWrapper<MemberOpenid> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberOpenid> buildQueryWrapper(MemberOpenidBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberOpenid> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, MemberOpenid::getId, bo.getId());
        lqw.orderByDesc(MemberOpenid::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            MemberOpenid::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), MemberOpenid::getState, bo.getState());
        lqw.eq(bo.getMemberId() != null, MemberOpenid::getMemberId, bo.getMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), MemberOpenid::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getPlatform()), MemberOpenid::getPlatform, bo.getPlatform());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenId()), MemberOpenid::getOpenId, bo.getOpenId());
        return lqw;
    }

    /**
     * 新增会员OpenId
     *
     * @param bo 会员OpenId
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(MemberOpenidBo bo) {
        MemberOpenid add = MapstructUtils.convert(bo, MemberOpenid.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会员OpenId
     *
     * @param bo 会员OpenId
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(MemberOpenidBo bo) {
        MemberOpenid update = MapstructUtils.convert(bo, MemberOpenid.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberOpenid entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除会员OpenId信息
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

    @Override
    public MemberOpenidVo queryByOpenInfo(String appId, String openId) {
        return baseMapper.selectVoOne(
            new LambdaQueryWrapper<MemberOpenid>()
                .eq(MemberOpenid::getAppId, appId)
                .eq(MemberOpenid::getOpenId, openId));
    }
}
