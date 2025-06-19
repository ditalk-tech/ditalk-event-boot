package org.dromara.module.config.service.impl;

import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.module.config.domain.bo.ConfigInfoBo;
import org.dromara.module.config.domain.vo.ConfigInfoVo;
import org.dromara.module.config.domain.ConfigInfo;
import org.dromara.module.config.mapper.ConfigInfoMapper;
import org.dromara.module.config.service.IConfigInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 配置信息Service业务层处理
 *
 * @author weidixian
 * @date 2025-06-12
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ConfigInfoServiceImpl implements IConfigInfoService {

    private final ConfigInfoMapper baseMapper;

    /**
     * 查询配置信息
     *
     * @param id 主键
     * @return 配置信息
     */
    @Override
    public ConfigInfoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询配置信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 配置信息分页列表
     */
    @Override
    public TableDataInfo<ConfigInfoVo> queryPageList(ConfigInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigInfo> lqw = buildQueryWrapper(bo);
        Page<ConfigInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的配置信息列表
     *
     * @param bo 查询条件
     * @return 配置信息列表
     */
    @Override
    public List<ConfigInfoVo> queryList(ConfigInfoBo bo) {
        LambdaQueryWrapper<ConfigInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigInfo> buildQueryWrapper(ConfigInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ConfigInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, ConfigInfo::getId, bo.getId());
        lqw.orderByDesc(ConfigInfo::getId);
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ConfigInfo::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ConfigInfo::getState, bo.getState());
        lqw.like(StringUtils.isNotBlank(bo.getName()), ConfigInfo::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), ConfigInfo::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getValue()), ConfigInfo::getValue, bo.getValue());
        return lqw;
    }

    /**
     * 新增配置信息
     *
     * @param bo 配置信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ConfigInfoBo bo) {
        ConfigInfo add = MapstructUtils.convert(bo, ConfigInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改配置信息
     *
     * @param bo 配置信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ConfigInfoBo bo) {
        ConfigInfo update = MapstructUtils.convert(bo, ConfigInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ConfigInfo entity) {
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
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        for (Long id : ids) {
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
    public List<ConfigInfoVo> queryList(ConfigInfoBo bo, IdPageQuery pageQuery) {
        LambdaQueryWrapper<ConfigInfo> lqw = Wrappers.lambdaQuery();
        lqw.lt(pageQuery.getId() != null, ConfigInfo::getId, pageQuery.getId());
        lqw.orderByDesc(ConfigInfo::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getState()), ConfigInfo::getState, bo.getState());
        return baseMapper.selectVoList(pageQuery.build(lqw));
    }

    @Override
    public ConfigInfoVo queryOneByKey(String key) {
        LambdaQueryWrapper<ConfigInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(ConfigInfo::getCode, key);
        return baseMapper.selectVoOne(lqw);
    }

}
