package org.dromara.module.config.service;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import jakarta.validation.constraints.NotNull;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.config.domain.bo.UniAuthConfigBo;
import org.dromara.module.config.domain.vo.UniAuthConfigVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;

/**
 * 小程序变量Service接口
 *
 * @author weidixian
 * @date 2025-06-18
 */
public interface IUniAuthConfigService {

    /**
     * 查询小程序变量
     *
     * @param id 主键
     * @return 小程序变量
     */
    @Cacheable(cacheNames = CacheNames.UniAuthConfig, key = "#id")
    UniAuthConfigVo queryById(Long id);

    /**
     * 分页查询小程序变量列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 小程序变量分页列表
     */
    TableDataInfo<UniAuthConfigVo> queryPageList(UniAuthConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的小程序变量列表
     *
     * @param bo 查询条件
     * @return 小程序变量列表
     */
    List<UniAuthConfigVo> queryList(UniAuthConfigBo bo);

    /**
     * 新增小程序变量
     *
     * @param bo 小程序变量
     * @return 是否新增成功
     */
    Boolean insertByBo(UniAuthConfigBo bo);

    /**
     * 修改小程序变量
     *
     * @param bo 小程序变量
     * @return 是否修改成功
     */
    @CacheEvict(cacheNames = CacheNames.UniAuthConfig, key = "#bo.id")
    Boolean updateByBo(UniAuthConfigBo bo);

    /**
     * 删除小程序变量
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @CacheEvict(cacheNames = CacheNames.UniAuthConfig, key = "#id")
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除小程序变量信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @DSTransactional
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询小程序变量列表
     *
     * @param bo 查询条件
     * @return 小程序变量列表
     */
    List<UniAuthConfigVo> queryList(UniAuthConfigBo bo, IdPageQuery pageQuery);

    String getSecret(@NotNull String platform, @NotNull String appid);
}
