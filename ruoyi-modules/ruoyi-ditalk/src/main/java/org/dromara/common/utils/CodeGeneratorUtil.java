package org.dromara.common.utils;

import org.dromara.common.redis.utils.RedisUtils;

/**
 * 编号生成器
 *
 * @author weidixian
 */
public class CodeGeneratorUtil
{
    private static final String COUNTER_KEY = "CodeGenerator:FourDigit";
    private static final long MIN = 1000;
    private static final long MAX = 9000;

    public static Long next4digit() {
        if (!RedisUtils.isExistsObject(COUNTER_KEY)) {
            RedisUtils.setAtomicValue(COUNTER_KEY, MIN);
        }
        long current = RedisUtils.incrAtomicValue(COUNTER_KEY);
        if (current >= MAX) {
            RedisUtils.setAtomicValue(COUNTER_KEY, MIN);
        }
        return current;
    }
}
