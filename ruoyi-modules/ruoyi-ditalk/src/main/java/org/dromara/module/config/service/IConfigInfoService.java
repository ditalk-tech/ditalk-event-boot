package org.dromara.module.config.service;

import org.dromara.module.config.domain.vo.ConfigInfoVo;
import org.dromara.module.config.domain.bo.ConfigInfoBo;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.constant.CacheNames;

import java.util.Collection;
import java.util.List;

/**
 * 配置信息Service接口
 *
 * @author weidixian
 * @date 2025-06-12
 */
public interface IConfigInfoService {

    /**
     * 查询配置信息
     *
     * @param id 主键
     * @return 配置信息
     */
    @Cacheable(cacheNames = CacheNames.ConfigInfo, key = "#id")
    ConfigInfoVo queryById(Long id);

    /**
     * 分页查询配置信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 配置信息分页列表
     */
    TableDataInfo<ConfigInfoVo> queryPageList(ConfigInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的配置信息列表
     *
     * @param bo 查询条件
     * @return 配置信息列表
     */
    List<ConfigInfoVo> queryList(ConfigInfoBo bo);

    /**
     * 新增配置信息
     *
     * @param bo 配置信息
     * @return 是否新增成功
     */
    Boolean insertByBo(ConfigInfoBo bo);

    /**
     * 修改配置信息
     *
     * @param bo 配置信息
     * @return 是否修改成功
     */
    @CacheEvict(cacheNames = CacheNames.ConfigInfo, key = "#bo.id")
    Boolean updateByBo(ConfigInfoBo bo);

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @CacheEvict(cacheNames = CacheNames.ConfigInfo, key = "#id")
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除配置信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @DSTransactional
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询配置信息列表
     *
     * @param bo 查询条件
     * @return 配置信息列表
     */
    List<ConfigInfoVo> queryList(ConfigInfoBo bo, IdPageQuery pageQuery);

    @Cacheable(cacheNames = CacheNames.ConfigInfo_Code, key = "#code")
    ConfigInfoVo queryOneByCode(String code);
}
