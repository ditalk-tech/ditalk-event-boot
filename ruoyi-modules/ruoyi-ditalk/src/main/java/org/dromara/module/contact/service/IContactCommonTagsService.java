package org.dromara.module.contact.service;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.contact.domain.bo.ContactCommonTagsBo;
import org.dromara.module.contact.domain.vo.ContactCommonTagsVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;

/**
 * 常用标签Service接口
 *
 * @author weidixian
 * @date 2025-06-11
 */
public interface IContactCommonTagsService {

    /**
     * 查询常用标签
     *
     * @param id 主键
     * @return 常用标签
     */
    @Cacheable(cacheNames = CacheNames.ContactCommonTags, key = "#id")
    ContactCommonTagsVo queryById(Long id);

    /**
     * 分页查询常用标签列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 常用标签分页列表
     */
    TableDataInfo<ContactCommonTagsVo> queryPageList(ContactCommonTagsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的常用标签列表
     *
     * @param bo 查询条件
     * @return 常用标签列表
     */
    List<ContactCommonTagsVo> queryList(ContactCommonTagsBo bo);

    /**
     * 新增常用标签
     *
     * @param bo 常用标签
     * @return 是否新增成功
     */
    Boolean insertByBo(ContactCommonTagsBo bo);

    /**
     * 修改常用标签
     *
     * @param bo 常用标签
     * @return 是否修改成功
     */
    @CacheEvict(cacheNames = CacheNames.ContactCommonTags, key = "#bo.id")
    Boolean updateByBo(ContactCommonTagsBo bo);

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @CacheEvict(cacheNames = CacheNames.ContactCommonTags, key = "#id")
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
    List<ContactCommonTagsVo> queryList(ContactCommonTagsBo bo, IdPageQuery pageQuery);
}
