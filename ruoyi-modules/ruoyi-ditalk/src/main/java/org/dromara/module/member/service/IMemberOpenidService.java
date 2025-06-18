package org.dromara.module.member.service;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import jakarta.validation.constraints.NotBlank;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.member.domain.bo.MemberOpenidBo;
import org.dromara.module.member.domain.vo.MemberOpenidVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;

/**
 * 会员OpenIdService接口
 *
 * @author weidixian
 * @date 2025-06-06
 */
public interface IMemberOpenidService {

    /**
     * 查询会员OpenId
     *
     * @param id 主键
     * @return 会员OpenId
     */
    @Cacheable(cacheNames = CacheNames.MemberOpenid, key = "#id")
    MemberOpenidVo queryById(Long id);

    /**
     * 分页查询会员OpenId列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会员OpenId分页列表
     */
    TableDataInfo<MemberOpenidVo> queryPageList(MemberOpenidBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会员OpenId列表
     *
     * @param bo 查询条件
     * @return 会员OpenId列表
     */
    List<MemberOpenidVo> queryList(MemberOpenidBo bo);

    /**
     * 新增会员OpenId
     *
     * @param bo 会员OpenId
     * @return 是否新增成功
     */
    Boolean insertByBo(MemberOpenidBo bo);

    /**
     * 修改会员OpenId
     *
     * @param bo 会员OpenId
     * @return 是否修改成功
     */
    @CacheEvict(cacheNames = CacheNames.MemberOpenid, key = "#bo.id")
    Boolean updateByBo(MemberOpenidBo bo);

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @CacheEvict(cacheNames = CacheNames.MemberOpenid, key = "#id")
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
    List<MemberOpenidVo> queryList(MemberOpenidBo bo, IdPageQuery pageQuery);

    MemberOpenidVo queryByOpenInfo(@NotBlank String appId, @NotBlank String openId);
}
