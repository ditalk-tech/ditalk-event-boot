package org.dromara.vip.member.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员OpenId对象 member_openid
 *
 * @author weidixian
 * @date 2025-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_openid")
public class MemberOpenid extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 状态
     */
    private String state;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * App应用ID
     */
    private String appId;

    /**
     * 平台代码
     */
    private String platform;

    /**
     * OpenID
     */
    private String openId;


}
