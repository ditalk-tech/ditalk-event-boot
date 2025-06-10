package org.dromara.module.member.domain.vo;

import org.dromara.module.member.domain.MemberOpenid;
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
 * 会员OpenId视图对象 member_openid
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberOpenid.class)
public class MemberOpenidVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
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
     * 会员ID
     */
    @ExcelProperty(value = "会员ID")
    private Long memberId;

    /**
     * App应用ID
     */
    @ExcelProperty(value = "App应用ID")
    private String appId;

    /**
     * 平台代码
     */
    @ExcelProperty(value = "平台代码")
    private String platform;

    /**
     * OpenID
     */
    @ExcelProperty(value = "OpenID")
    private String openId;


}
