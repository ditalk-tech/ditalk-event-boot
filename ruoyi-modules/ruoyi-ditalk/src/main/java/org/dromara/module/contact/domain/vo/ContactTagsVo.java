package org.dromara.module.contact.domain.vo;

import org.dromara.module.contact.domain.ContactTags;
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
 * 联系人标签视图对象 contact_tags
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ContactTags.class)
public class ContactTagsVo implements Serializable {

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
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;

    /**
     * 联系人ID
     */
    @ExcelProperty(value = "联系人ID")
    private Long contactId;

    /**
     * 标签分类
     */
    @ExcelProperty(value = "标签分类")
    private String category;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String name;


}
