package org.dromara.vip.member.service;

import jakarta.validation.constraints.NotBlank;
import org.dromara.vip.member.domain.vo.MemberOpenidVo;
import org.dromara.vip.member.domain.bo.MemberOpenidBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

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
    Boolean updateByBo(MemberOpenidBo bo);

    /**
     * 校验并批量删除会员OpenId信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    MemberOpenidVo queryByOpenInfo(@NotBlank String appId, @NotBlank String openId);
}
