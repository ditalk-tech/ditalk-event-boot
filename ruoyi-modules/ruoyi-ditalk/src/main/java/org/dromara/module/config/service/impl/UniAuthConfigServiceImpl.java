package org.dromara.module.config.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.NotBlank;
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
import org.dromara.module.config.domain.UniAuthConfig;
import org.dromara.module.config.domain.bo.UniAuthConfigBo;
import org.dromara.module.config.domain.vo.UniAuthConfigVo;
import org.dromara.module.config.mapper.UniAuthConfigMapper;
import org.dromara.module.config.service.IUniAuthConfigService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 小程序变量Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UniAuthConfigServiceImpl implements IUniAuthConfigService {

    private final UniAuthConfigMapper baseMapper;

    /**
     * 查询小程序变量
     *
     * @param id 主键
     * @return 小程序变量
     */
    @Override
    @Cacheable(cacheNames = CacheNames.UniAuthConfig, key = "#id")
    public UniAuthConfigVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询小程序变量列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 小程序变量分页列表
     */
    @Override
    public TableDataInfo<UniAuthConfigVo> queryPageList(UniAuthConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UniAuthConfig> lqw = buildQueryWrapper(bo);
        Page<UniAuthConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的小程序变量列表
     *
     * @param bo 查询条件
     * @return 小程序变量列表
     */
    @Override
    public List<UniAuthConfigVo> queryList(UniAuthConfigBo bo) {
        LambdaQueryWrapper<UniAuthConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UniAuthConfig> buildQueryWrapper(UniAuthConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UniAuthConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, UniAuthConfig::getId, bo.getId());
        lqw.orderByDesc(UniAuthConfig::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            UniAuthConfig::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.like(StringUtils.isNotBlank(bo.getPlatform()), UniAuthConfig::getPlatform, bo.getPlatform());
        lqw.like(StringUtils.isNotBlank(bo.getAppId()), UniAuthConfig::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getState()), UniAuthConfig::getState, bo.getState());
        return lqw;
    }

    /**
     * 新增小程序变量
     *
     * @param bo 小程序变量
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(UniAuthConfigBo bo) {
        UniAuthConfig add = MapstructUtils.convert(bo, UniAuthConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改小程序变量
     *
     * @param bo 小程序变量
     * @return 是否修改成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.UniAuthConfig, key = "#bo.id")
    public Boolean updateByBo(UniAuthConfigBo bo) {
        UniAuthConfig update = MapstructUtils.convert(bo, UniAuthConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UniAuthConfig entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 删除小程序变量
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.UniAuthConfig, key = "#id")
    public Boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 校验并批量删除小程序变量信息
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
     * 通过ID分页查询小程序变量列表
     *
     * @param bo 查询条件
     * @return 小程序变量列表
     */
    @Override
    public List<UniAuthConfigVo> queryList(UniAuthConfigBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<UniAuthConfig> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, UniAuthConfig::getId, pageQuery.getId());
        lqw.orderByDesc(UniAuthConfig::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), UniAuthConfig::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

    @Override
    public String getSecret(@NotBlank String platform,@NotBlank String appid) {
        UniAuthConfig uniAuthConfig = baseMapper.selectOne(new LambdaQueryWrapper<UniAuthConfig>()
            .eq(UniAuthConfig::getPlatform, platform)
            .eq(UniAuthConfig::getAppId, appid)
            .eq(UniAuthConfig::getState, CommonConstants.AVAILABLE));
        return uniAuthConfig.getSecret();
    }

}
