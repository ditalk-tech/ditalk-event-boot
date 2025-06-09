package org.dromara.vip.news.domain.bo;

import org.dromara.vip.news.domain.NewsInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 喜讯新闻业务对象 news_info
 *
 * @author weidixian
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = NewsInfo.class, reverseConvertGenerate = false)
public class NewsInfoBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 乐观锁
     */
    @NotNull(message = "乐观锁不能为空", groups = { EditGroup.class })
    private Long version;

    /**
     * 状态（sys_normal_disable）
     */
    @NotBlank(message = "状态（sys_normal_disable）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String state;

    /**
     * 发生时间
     */
    @NotNull(message = "发生时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date eventTime;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;


}
