package org.dromara.vip.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dromara.common.core.utils.StringUtils;

/**
 * 设备类型
 * 针对多套 用户体系
 *
 * @author weidixian
 */
@Getter
@AllArgsConstructor
public enum UniRoleKeyEnum {

    /**
     * Uni小程序客户端
     */
    MP_WEIXIN("mp_weixin");


    private final String code;

    public static UniRoleKeyEnum getCode(String str) {
        for (UniRoleKeyEnum value : values()) {
            if (StringUtils.contains(str, value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("'RoleKey' not found By " + str);
    }

    // 用于 @SaCheckRole
    public final static String MP_WEIXIN_STR = "mp_weixin";

}
