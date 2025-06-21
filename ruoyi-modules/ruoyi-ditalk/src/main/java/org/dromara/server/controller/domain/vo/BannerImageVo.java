package org.dromara.server.controller.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 首页横幅图片
 *
 * @author weidixian
 **/
@Data
public class BannerImageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * OSS文件ID
     */
    private Long ossId;

    /**
     * 图片URL
     */
    private String url;
}
