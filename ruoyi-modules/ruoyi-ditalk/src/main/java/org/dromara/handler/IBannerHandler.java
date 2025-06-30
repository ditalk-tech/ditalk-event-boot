package org.dromara.handler;

import org.dromara.app.domain.bo.BannerImageBo;

/**
 * 首页横幅应用接口
 *
 * @author weidixian
 */
public interface IBannerHandler {

    String getImageUrl(String key);

    int edit(BannerImageBo bo);
}
