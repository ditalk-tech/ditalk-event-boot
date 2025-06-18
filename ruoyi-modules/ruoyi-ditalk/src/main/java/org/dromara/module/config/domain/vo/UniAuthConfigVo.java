package org.dromara.module.config.domain.vo;

import org.dromara.module.config.domain.UniAuthConfig;
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
 * 小程序变量视图对象 uni_auth_config
 *
 * @author weidixian
 * @date 2025-06-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = UniAuthConfig.class)
public class UniAuthConfigVo implements Serializable {

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
     * 平台名称
     */
    @ExcelProperty(value = "平台名称")
    private String platform;

    /**
     * AppId
     */
    @ExcelProperty(value = "AppId")
    private String appId;

    /**
     * App密钥
     */
    @ExcelProperty(value = "App密钥")
    private String secret;

    /**
     * 状态（sys_normal_disable）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注信息")
    private String remark;


}
