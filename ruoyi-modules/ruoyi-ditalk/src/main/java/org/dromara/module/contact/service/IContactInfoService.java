package org.dromara.module.contact.service;

import org.dromara.module.contact.domain.vo.ContactInfoVo;
import org.dromara.module.contact.domain.bo.ContactInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 联系人Service接口
 *
 * @author weidixian
 * @date 2025-06-10
 */
public interface IContactInfoService {

    /**
     * 查询联系人
     *
     * @param id 主键
     * @return 联系人
     */
    ContactInfoVo queryById(Long id);

    /**
     * 分页查询联系人列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 联系人分页列表
     */
    TableDataInfo<ContactInfoVo> queryPageList(ContactInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的联系人列表
     *
     * @param bo 查询条件
     * @return 联系人列表
     */
    List<ContactInfoVo> queryList(ContactInfoBo bo);

    /**
     * 新增联系人
     *
     * @param bo 联系人
     * @return 是否新增成功
     */
    Boolean insertByBo(ContactInfoBo bo);

    /**
     * 修改联系人
     *
     * @param bo 联系人
     * @return 是否修改成功
     */
    Boolean updateByBo(ContactInfoBo bo);

    /**
     * 校验并批量删除联系人信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
