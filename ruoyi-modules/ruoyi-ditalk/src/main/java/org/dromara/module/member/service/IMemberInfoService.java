package org.dromara.module.member.service;

import org.dromara.module.member.domain.vo.MemberInfoVo;
import org.dromara.module.member.domain.bo.MemberInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员信息Service接口
 *
 * @author weidixian
 * @date 2025-06-06
 */
public interface IMemberInfoService {

    /**
     * 查询会员信息
     *
     * @param id 主键
     * @return 会员信息
     */
    MemberInfoVo queryById(Long id);

    /**
     * 分页查询会员信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会员信息分页列表
     */
    TableDataInfo<MemberInfoVo> queryPageList(MemberInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会员信息列表
     *
     * @param bo 查询条件
     * @return 会员信息列表
     */
    List<MemberInfoVo> queryList(MemberInfoBo bo);

    /**
     * 新增会员信息
     *
     * @param bo 会员信息
     * @return 是否新增成功
     */
    Boolean insertByBo(MemberInfoBo bo);

    /**
     * 修改会员信息
     *
     * @param bo 会员信息
     * @return 是否修改成功
     */
    Boolean updateByBo(MemberInfoBo bo);

    /**
     * 校验并批量删除会员信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
