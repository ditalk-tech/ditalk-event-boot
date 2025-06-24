package org.dromara.common.constant;

/**
 * 缓存组名称常量
 * <p>
 * key 格式为 cacheNames#ttl#maxIdleTime#maxSize#local
 * <p>
 * ttl 过期时间 如果设置为0则不过期 默认为0
 * maxIdleTime 最大空闲时间 根据LRU算法清理空闲数据 如果设置为0则不检测 默认为0
 * maxSize 组最大长度 根据LRU算法清理溢出数据 如果设置为0则无限长 默认为0
 * local 默认开启本地缓存为1 关闭本地缓存为0
 * <p>
 * 例子: test#60s、test#0#60s、test#0#1m#1000、test#1h#0#500、test#1h#0#500#0
 *
 * @author weidixian
 */
public interface CacheNames {

    String PREFIX = "DOMAIN:";

    String ConfigInfo = PREFIX + "ConfigInfo#7d";
    String UniAuthConfig = PREFIX + "UniAuthConfig#7d";
    String NewsInfo = PREFIX + "NewsInfo#7d";
    String NewsInfo_Total = PREFIX + "NewsInfo_Total#7d";
    String MemberOpenid = PREFIX + "MemberOpenid#7d";
    String MemberInfo = PREFIX + "MemberInfo#7d";
    String ContactTags = PREFIX + "ContactTags#7d";
    String ContactLog = PREFIX + "ContactLog#7d";
    String ContactInfo = PREFIX + "ContactInfo#7d";
    String ConfigInfo_Code = PREFIX + "ConfigInfo_Code#7d";
    String ContactCommonTags = PREFIX + "ContactCommonTags#7d";
    String EventInfo = PREFIX + "EventInfo#7d";
    String EventMember = PREFIX + "EventMember#7d";
    String EventMoment = PREFIX + "EventMoment#7d";
    String EventMoment_EventId = PREFIX + "EventMoment_EventId#7d";
    String MemberPhoto = PREFIX + "MemberPhoto#7d";
}
