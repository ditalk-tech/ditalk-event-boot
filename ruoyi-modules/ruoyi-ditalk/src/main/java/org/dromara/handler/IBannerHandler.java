package org.dromara.handler;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 轮播图处理接口
 * @author weidixian
 */
public interface IBannerHandler {

    String getImageUrl(String key);

    int edit(@NotNull(message = "文件不能为空") Long ossId);
}
