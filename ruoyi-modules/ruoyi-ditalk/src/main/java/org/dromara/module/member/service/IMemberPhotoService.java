package org.dromara.module.member.service;

import org.dromara.module.member.domain.vo.MemberPhotoVo;
import org.dromara.module.member.domain.bo.MemberPhotoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.IdPageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员照片Service接口
 *
 * @author weidixian
 * @date 2025-06-24
 */
public interface IMemberPhotoService {

    /**
     * 查询会员照片
     *
     * @param id 主键
     * @return 会员照片
     */
    MemberPhotoVo queryById(Long id);

    /**
     * 分页查询会员照片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会员照片分页列表
     */
    TableDataInfo<MemberPhotoVo> queryPageList(MemberPhotoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会员照片列表
     *
     * @param bo 查询条件
     * @return 会员照片列表
     */
    List<MemberPhotoVo> queryList(MemberPhotoBo bo);

    /**
     * 新增会员照片
     *
     * @param bo 会员照片
     * @return 是否新增成功
     */
    Boolean insertByBo(MemberPhotoBo bo);

    /**
     * 修改会员照片
     *
     * @param bo 会员照片
     * @return 是否修改成功
     */
    Boolean updateByBo(MemberPhotoBo bo);

    /**
     * 删除会员照片
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除会员照片信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询会员照片列表
     *
     * @param bo 查询条件
     * @return 会员照片列表
     */
    List<MemberPhotoVo> queryList(MemberPhotoBo bo, IdPageQuery pageQuery);
}
