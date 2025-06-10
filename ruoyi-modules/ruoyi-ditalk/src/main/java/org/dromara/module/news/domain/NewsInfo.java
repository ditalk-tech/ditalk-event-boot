package org.dromara.module.news.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 喜讯新闻对象 news_info
 *
 * @author weidixian
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("news_info")
public class NewsInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 状态（sys_normal_disable）
     */
    private String state;

    /**
     * 发生时间
     */
    private Date eventTime;

    /**
     * 内容
     */
    private String content;


}
