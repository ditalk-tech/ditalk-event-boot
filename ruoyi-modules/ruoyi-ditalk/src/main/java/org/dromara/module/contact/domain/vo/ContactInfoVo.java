package org.dromara.module.contact.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.module.contact.domain.ContactInfo;
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
 * 联系人视图对象 contact_info
 *
 * @author weidixian
 * @date 2025-06-10
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ContactInfo.class)
public class ContactInfoVo implements Serializable {

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
     * 名字
     */
    @ExcelProperty(value = "名字")
    private String name;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickname;

    /**
     * 姓名拼音
     */
    @ExcelProperty(value = "姓名拼音")
    private String pinyin;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String mobile;

    /**
     * 电子邮件
     */
    @ExcelProperty(value = "电子邮件")
    private String email;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String gender;

    /**
     * 生日
     */
    @ExcelProperty(value = "生日")
    private Date birthday;

    /**
     * 户籍
     */
    @ExcelProperty(value = "户籍")
    private String placeOfOrigin;

    /**
     * 居住地
     */
    @ExcelProperty(value = "居住地")
    private String address;

    /**
     * 毕业学校
     */
    @ExcelProperty(value = "毕业学校")
    private String graduationSchool;

    /**
     * 学历
     */
    @ExcelProperty(value = "学历")
    private String qualification;

    /**
     * 公司组织
     */
    @ExcelProperty(value = "公司组织")
    private String organization;

    /**
     * 工作职务
     */
    @ExcelProperty(value = "工作职务")
    private String position;

    /**
     * 社会角色
     */
    @ExcelProperty(value = "社会角色")
    private String socialRole;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 最近接触时间
     */
    @ExcelProperty(value = "最近接触时间")
    private Date lastInteractionTime;

    /**
     * 接触频率
     */
    @ExcelProperty(value = "接触频率", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ditalk_interaction_frequency")
    private String interactionFrequency;


}
