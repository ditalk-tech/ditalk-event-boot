package org.dromara.uni.controller.domain.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;

/**
 * 喜讯新闻业务对象 news_info
 *
 * @author weidixian
 * @date 2025-06-09
 */
@Data
public class NewsInfoIdQueryBo {

    /**
     * 状态（sys_normal_disable）
     */
    @NotBlank(message = "状态（sys_normal_disable）不能为空", groups = {AddGroup.class, EditGroup.class})
    private String state;

    /**
     * 搜索值
     */
//    @JsonIgnore
//    @TableField(exist = false)
//    private String searchValue;

    /**
     * 请求参数
     */
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @TableField(exist = false)
//    private Map<String, Object> params = new HashMap<>();

}
