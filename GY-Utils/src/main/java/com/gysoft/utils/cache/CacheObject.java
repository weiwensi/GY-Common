package com.gysoft.utils.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周宁
 * @Date 2018-10-11 19:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheObject {
    /**
     * 缓存key
     */
    private String key;
    /**
     * 值
     */
    private Object value;
    /**
     * 过期时间
     */
    private long expired;

}
