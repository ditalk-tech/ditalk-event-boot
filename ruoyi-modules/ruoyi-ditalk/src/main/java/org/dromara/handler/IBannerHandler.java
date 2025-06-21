package org.dromara.handler;

import org.dromara.server.controller.domain.bo.BannerImageBo;

/**
 * 轮播图处理接口
 * @author weidixian
 */
public interface IBannerHandler {

    String getImageUrl(String key);

    int edit(BannerImageBo bo);
}
