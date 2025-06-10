package org.dromara.module.news.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.module.news.domain.NewsInfo;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 喜讯新闻视图对象 news_info
 *
 * @author weidixian
 * @date 2025-06-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = NewsInfo.class)
public class NewsInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 乐观锁
     */
    @ExcelProperty(value = "乐观锁")
    private Long version;

    /**
     * 状态（sys_normal_disable）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;

    /**
     * 发生时间
     */
    @ExcelProperty(value = "发生时间")
    private Date eventTime;

    /**
     * 内容
     */
    @ExcelProperty(value = "内容")
    private String content;


}
