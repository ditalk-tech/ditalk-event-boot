package org.dromara.vip.common.wx.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * wxpay pay properties.
 *
 * @author Binary Wang
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {

    /**
     * 多个公众号配置信息
     */
    private List<PayConfig> configs;

    @Data
    public static class PayConfig {
        /**
         * 设置微信公众号或者小程序等的appid
         */
        private String appId;

        /**
         * 微信支付商户号
         */
        private String mchId;

        /**
         * 微信支付商户密钥
         */
        private String mchKey;

        /**
         * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
         */
        private String subAppId;

        /**
         * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
         */
        private String subMchId;

        /**
         * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
         */
        private String keyPath;

        /**
         * apiV3Key 微信支付在 回调通知和平台证书下载接口中，对关键信息进行了AES-256-GCM加密。API v3密钥是加密时使用的对称密钥。
         */
        private String apiV3Key;
    }
}
