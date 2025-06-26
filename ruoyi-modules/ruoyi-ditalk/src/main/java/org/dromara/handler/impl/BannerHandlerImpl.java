package org.dromara.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CacheNames;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.constant.ConfigInfoCodeConstants;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.utils.ObjectUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.handler.IBannerHandler;
import org.dromara.module.config.domain.bo.ConfigInfoBo;
import org.dromara.module.config.domain.vo.ConfigInfoVo;
import org.dromara.module.config.service.IConfigInfoService;
import org.dromara.server.controller.domain.bo.BannerImageBo;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysOssService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页横幅应用接口
 *
 * @author weidixian
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerHandlerImpl implements IBannerHandler {

    private final IConfigInfoService configInfoService;
    private final ISysOssService sysOssService;

    @Override
    public String getImageUrl(String key) {
        return ObjectUtils.notNullGetter(configInfoService.queryOneByCode(key), ConfigInfoVo::getValue);
    }

    @Override
    public int edit(BannerImageBo bo) {
        SysOssVo oss = sysOssService.getById(bo.getOssId());
        if (ObjectUtils.isNull(oss)) {
            throw new UserException("未找到指定图片");
        }
        ConfigInfoBo configInfoBo = new ConfigInfoBo();
        configInfoBo.setState(CommonConstants.AVAILABLE);
        configInfoBo.setName(ConfigInfoCodeConstants.BannerImageName);
        configInfoBo.setCode(ConfigInfoCodeConstants.BannerImageCode);
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("ossId", oss.getOssId());
        valueMap.put("url", oss.getUrl());
        configInfoBo.setValue(JsonUtils.toJsonString(valueMap));
        ConfigInfoVo configInfoVo = configInfoService.queryOneByCode(ConfigInfoCodeConstants.BannerImageCode);
        if (ObjectUtils.isNull(configInfoVo)) {
            Boolean flag = configInfoService.insertByBo(configInfoBo);
            CacheUtils.evict(CacheNames.ConfigInfo, configInfoBo.getId()); // 程序上线没有数据时用户访问接口会设置为null，这里清除
            CacheUtils.evict(CacheNames.ConfigInfo_Code, configInfoBo.getCode()); // 程序上线没有数据时用户访问接口会设置为null，这里清除
            return flag ? 1 : 0;
        } else {
            configInfoBo.setId(configInfoVo.getId());
            configInfoBo.setVersion(bo.getVersion());
            return configInfoService.updateByBo(configInfoBo) ? 1 : 0;
        }
    }
}
