package org.dromara.module.member.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.module.member.domain.MemberPhoto;
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
 * 会员照片视图对象 member_photo
 *
 * @author weidixian
 * @date 2025-06-24
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberPhoto.class)
public class MemberPhotoVo implements Serializable {

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
     * 照片Id
     */
    @ExcelProperty(value = "photoId")
    private Long photoId;

    /**
     * 照片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "photoId")
    private String photoUrl;

    /**
     * 会员ID
     */
    @ExcelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 图片描述
     */
    @ExcelProperty(value = "图片描述")
    private String caption;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;


}
