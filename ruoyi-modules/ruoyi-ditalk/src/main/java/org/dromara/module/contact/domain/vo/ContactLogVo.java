package org.dromara.module.contact.domain.vo;

import org.dromara.module.contact.domain.ContactLog;
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
 * 联系记录视图对象 contact_log
 *
 * @author weidixian
 * @date 2025-06-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ContactLog.class)
public class ContactLogVo implements Serializable {

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
     * 经办人ID
     */
    @ExcelProperty(value = "经办人ID")
    private Long operatorId;

    /**
     * 通讯渠道
     */
    @ExcelProperty(value = "通讯渠道", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ditalk_contact_channel")
    private String channel;

    /**
     * 主题
     */
    @ExcelProperty(value = "主题")
    private String subject;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;


}
