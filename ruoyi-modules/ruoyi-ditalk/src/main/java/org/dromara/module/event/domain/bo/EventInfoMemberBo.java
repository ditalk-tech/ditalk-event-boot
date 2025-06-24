package org.dromara.module.event.domain.bo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 活动信息中的活动名单快照对象
 *
 * @author weidixian
 */

@Data
public class EventInfoMemberBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String sex;
    private String avatar;
}
