package org.dromara.vip.news.service;

import org.dromara.vip.news.domain.vo.NewsInfoVo;
import org.dromara.vip.news.domain.bo.NewsInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 喜讯新闻Service接口
 *
 * @author weidixian
 * @date 2025-06-09
 */
public interface INewsInfoService {

    /**
     * 查询喜讯新闻
     *
     * @param id 主键
     * @return 喜讯新闻
     */
    NewsInfoVo queryById(Long id);

    /**
     * 分页查询喜讯新闻列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 喜讯新闻分页列表
     */
    TableDataInfo<NewsInfoVo> queryPageList(NewsInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的喜讯新闻列表
     *
     * @param bo 查询条件
     * @return 喜讯新闻列表
     */
    List<NewsInfoVo> queryList(NewsInfoBo bo);

    /**
     * 新增喜讯新闻
     *
     * @param bo 喜讯新闻
     * @return 是否新增成功
     */
    Boolean insertByBo(NewsInfoBo bo);

    /**
     * 修改喜讯新闻
     *
     * @param bo 喜讯新闻
     * @return 是否修改成功
     */
    Boolean updateByBo(NewsInfoBo bo);

    /**
     * 校验并批量删除喜讯新闻信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
