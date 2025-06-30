package org.dromara.app.domain.bo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 首页横幅图片
 *
 * @author weidixian
 **/
@Data
public class BannerImageBo {
    /**
     * 版本号
     */
    private Long version;

    /**
     * OSS文件ID
     */
    @NotNull(message = "文件不能为空")
    private Long ossId;
}
