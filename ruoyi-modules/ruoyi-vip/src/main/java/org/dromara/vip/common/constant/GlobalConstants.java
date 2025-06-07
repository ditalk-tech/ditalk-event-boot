package org.dromara.vip.common.constant;

/**
 * 全局的key常量 (业务无关的key)
 *
 * @author weidixian
 */
public interface GlobalConstants {

    /**
     * 小程序认证 redis key
     */
    String MP_AUTH_KEY = org.dromara.common.core.constant.GlobalConstants.GLOBAL_REDIS_KEY + "mp_auth:";
}
