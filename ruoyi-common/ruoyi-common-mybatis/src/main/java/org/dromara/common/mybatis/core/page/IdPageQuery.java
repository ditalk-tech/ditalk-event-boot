package org.dromara.common.mybatis.core.page;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通过ID分页查询实体类
 *
 * @author weidixian
 */

@Data
public class IdPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    private Long id;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 每页显示记录数 默认值 默认查16条
     */
    public static final int DEFAULT_PAGE_SIZE = 16;

    public <T> LambdaQueryWrapper<T> build(LambdaQueryWrapper<T> lqw) {
        Integer pageSize = ObjectUtil.defaultIfNull(getPageSize(), DEFAULT_PAGE_SIZE);
        lqw.last("limit " + pageSize);
        return lqw;
    }


}
