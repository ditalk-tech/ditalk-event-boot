package org.dromara.module.event.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.module.event.domain.EventMoment;
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
 * 活动瞬间视图对象 event_moment
 *
 * @author weidixian
 * @date 2025-06-23
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EventMoment.class)
public class EventMomentVo implements Serializable {

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
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long eventId;

    /**
     * 图片ID
     */
    @ExcelProperty(value = "图片ID")
    private Long image;

    /**
     * 图片IDUrl
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "image")
    private String imageUrl;
    /**
     * 摘要
     */
    @ExcelProperty(value = "摘要")
    private String summary;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;


}
