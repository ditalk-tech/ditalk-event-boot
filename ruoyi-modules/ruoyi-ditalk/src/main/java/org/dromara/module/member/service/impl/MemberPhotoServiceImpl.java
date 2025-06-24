package org.dromara.module.member.service.impl;

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
import org.dromara.module.member.domain.MemberPhoto;
import org.dromara.module.member.domain.bo.MemberPhotoBo;
import org.dromara.module.member.domain.vo.MemberPhotoVo;
import org.dromara.module.member.service.IMemberPhotoService;
import org.dromara.module.member.mapper.MemberPhotoMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 会员照片Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-24
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberPhotoServiceImpl implements IMemberPhotoService {

    private final MemberPhotoMapper baseMapper;

    /**
     * 查询会员照片
     *
     * @param id 主键
     * @return 会员照片
     */
    @Override
    @Cacheable(cacheNames = CacheNames.MemberPhoto, key = "#id")
    public MemberPhotoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询会员照片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会员照片分页列表
     */
    @Override
    public TableDataInfo<MemberPhotoVo> queryPageList(MemberPhotoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberPhoto> lqw = buildQueryWrapper(bo);
        Page<MemberPhotoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会员照片列表
     *
     * @param bo 查询条件
     * @return 会员照片列表
     */
    @Override
    public List<MemberPhotoVo> queryList(MemberPhotoBo bo) {
        LambdaQueryWrapper<MemberPhoto> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberPhoto> buildQueryWrapper(MemberPhotoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberPhoto> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, MemberPhoto::getId, bo.getId());
        lqw.orderByDesc(MemberPhoto::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            MemberPhoto::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(bo.getPhotoId() != null, MemberPhoto::getPhotoId, bo.getPhotoId());
        lqw.eq(bo.getMemberId() != null, MemberPhoto::getMemberId, bo.getMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getState()), MemberPhoto::getState, bo.getState());
        return lqw;
    }

    /**
     * 新增会员照片
     *
     * @param bo 会员照片
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(MemberPhotoBo bo) {
        MemberPhoto add = MapstructUtils.convert(bo, MemberPhoto.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会员照片
     *
     * @param bo 会员照片
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.MemberPhoto, key = "#bo.id")
    public Boolean updateByBo(MemberPhotoBo bo) {
        MemberPhoto update = MapstructUtils.convert(bo, MemberPhoto.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberPhoto entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除会员照片
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.MemberPhoto, key = "#id")
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 校验并批量删除会员照片信息
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
     * 通过ID分页查询会员照片列表
     *
     * @param bo 查询条件
     * @return 会员照片列表
     */
    @Override
    public List<MemberPhotoVo> queryList(MemberPhotoBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<MemberPhoto> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, MemberPhoto::getId, pageQuery.getId());
        lqw.orderByDesc(MemberPhoto::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), MemberPhoto::getState, bo.getState());
        lqw.eq(bo.getMemberId() != null, MemberPhoto::getMemberId, bo.getMemberId());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

}
