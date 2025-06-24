package org.dromara.module.event.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.module.event.domain.EventInfo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 活动信息视图对象 event_info
 *
 * @author weidixian
 * @date 2025-06-22
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EventInfo.class)
public class EventInfoVo implements Serializable {

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
     * 封面图片
     */
    @ExcelProperty(value = "封面图片")
    private Long coverImage;

    /**
     * 封面图片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "coverImage")
    private String coverImageUrl;
    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 报名截止
     */
    @ExcelProperty(value = "报名截止")
    private Date applicationDeadline;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 名额
     */
    @ExcelProperty(value = "名额")
    private Long quota;

    /**
     * 地点
     */
    @ExcelProperty(value = "地点")
    private String location;

    /**
     * 活动安排
     */
    @ExcelProperty(value = "活动安排")
    private String arrangement;

    /**
     * 报名会员快照
     */
    @ExcelProperty(value = "报名会员快照")
    private String members;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注信息")
    private String remark;

    /**
     * 扩展信息
     */
    @ExcelProperty(value = "扩展信息")
    private String exInfo;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String state;

    private List<EventMomentVo> eventMoments;
}
