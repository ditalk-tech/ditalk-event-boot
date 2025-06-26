package org.dromara.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dromara.common.core.utils.StringUtils;

/**
 * 会员状态枚举
 *
 * @author weidixian
 */
@Getter
@AllArgsConstructor
public enum MemberInfoStateEnum {

    OFFLINE("0"),
    ACTIVITY("1");


    private final String code;

    public static MemberInfoStateEnum getCode(String str) {
        for (MemberInfoStateEnum value : values()) {
            if (StringUtils.contains(str, value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("找不到值为 " + str + " 的会员状态枚举");
    }

}
