package org.dromara.module.contact.service;

import org.dromara.common.mybatis.core.page.IdPageQuery;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.module.contact.domain.bo.ContactLogBo;
import org.dromara.module.contact.domain.vo.ContactLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 联系记录Service接口
 *
 * @author weidixian
 * @date 2025-06-11
 */
public interface IContactLogService {

    /**
     * 查询联系记录
     *
     * @param id 主键
     * @return 联系记录
     */
    ContactLogVo queryById(Long id);

    /**
     * 分页查询联系记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 联系记录分页列表
     */
    TableDataInfo<ContactLogVo> queryPageList(ContactLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的联系记录列表
     *
     * @param bo 查询条件
     * @return 联系记录列表
     */
    List<ContactLogVo> queryList(ContactLogBo bo);

    /**
     * 新增联系记录
     *
     * @param bo 联系记录
     * @return 是否新增成功
     */
    Boolean insertByBo(ContactLogBo bo);

    /**
     * 修改联系记录
     *
     * @param bo 联系记录
     * @return 是否修改成功
     */
    Boolean updateByBo(ContactLogBo bo);

    /**
     * 删除配置信息
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteById(Long id);

    /**
     * 校验并批量删除配置信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 通过ID分页查询配置信息列表
     *
     * @param bo 查询条件
     * @return 配置信息列表
     */
    List<ContactLogVo> queryList(ContactLogBo bo, IdPageQuery pageQuery);
}
