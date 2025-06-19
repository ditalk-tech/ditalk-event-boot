package org.dromara.handler.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.constant.CommonConstants;
import org.dromara.common.constant.ConfigInfoCodeConstants;
import org.dromara.common.core.exception.user.UserException;
import org.dromara.common.core.utils.ObjectUtils;
import org.dromara.module.config.domain.bo.ConfigInfoBo;
import org.dromara.module.config.domain.vo.ConfigInfoVo;
import org.dromara.module.config.service.IConfigInfoService;
import org.dromara.handler.IBannerHandler;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysOssService;
import org.springframework.stereotype.Service;

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
    public int edit(Long ossId) {
        SysOssVo oss = sysOssService.getById(ossId);
        if (ObjectUtils.isNull(oss)) {
            throw new UserException("未找到指定图片");
        }
        ConfigInfoBo bo = new ConfigInfoBo();
        bo.setState(CommonConstants.AVAILABLE);
        bo.setName(ConfigInfoCodeConstants.BannerImageName);
        bo.setCode(ConfigInfoCodeConstants.BannerImageCode);
        bo.setValue(oss.getUrl());
        ConfigInfoVo configInfoVo = configInfoService.queryOneByCode(ConfigInfoCodeConstants.BannerImageCode);
        if (ObjectUtils.isNull(configInfoVo)) {
            return configInfoService.insertByBo(bo)? 1 : 0;
        } else {
            bo.setId(configInfoVo.getId());
            return configInfoService.updateByBo(bo)? 1 : 0;
        }
    }
}
