package org.dromara.module.config.domain.vo;

import org.dromara.module.config.domain.ConfigInfo;
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
 * 配置信息视图对象 config_info
 *
 * @author weidixian
 * @date 2025-06-12
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigInfo.class)
public class ConfigInfoVo implements Serializable {

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
     * 配置项名称
     */
    @ExcelProperty(value = "配置项名称")
    private String name;

    /**
     * 配置项键
     */
    @ExcelProperty(value = "配置项键")
    private String key;

    /**
     * 配置项JSON值
     */
    @ExcelProperty(value = "配置项JSON值")
    private String value;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
