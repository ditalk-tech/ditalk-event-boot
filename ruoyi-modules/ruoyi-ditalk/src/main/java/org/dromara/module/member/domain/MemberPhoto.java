package org.dromara.module.member.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员照片对象 member_photo
 *
 * @author weidixian
 * @date 2025-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_photo")
public class MemberPhoto extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
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
     * 照片Id
     */
    private Long photoId;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 图片描述
     */
    private String caption;

    /**
     * 状态
     */
    private String state;


}
