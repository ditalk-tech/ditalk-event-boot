package org.dromara.vip.common.wx.pay.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Weidixian
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
@AllArgsConstructor
public class WxPayConfiguration {
    private WxPayProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() {
        // 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的IDE需要引入lombok插件！！！！
        final List<WxPayProperties.PayConfig> configs = this.properties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("微信支付配置有误！");
        }
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setMultiConfig(configs.stream().map(props -> {
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(StringUtils.trimToNull(props.getAppId()));
            payConfig.setMchId(StringUtils.trimToNull(props.getMchId()));
            payConfig.setMchKey(StringUtils.trimToNull(props.getMchKey()));
            payConfig.setSubAppId(StringUtils.trimToNull(props.getSubAppId()));
            payConfig.setSubMchId(StringUtils.trimToNull(props.getSubMchId()));
            payConfig.setKeyPath(StringUtils.trimToNull(props.getKeyPath()));
            payConfig.setApiV3Key(StringUtils.trimToNull(props.getApiV3Key()));
            // 可以指定是否使用沙箱环境
            payConfig.setUseSandboxEnv(false);
            return payConfig;
            // }).collect(Collectors.toMap(WxPayConfig::getMchId, config -> config)));
        }).collect(Collectors.toMap(WxPayConfig::getAppId, config -> config)));
        return wxPayService;
    }

}
