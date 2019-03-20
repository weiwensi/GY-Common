package com.gysoft.utils.test.commons.collections;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 周宁
 * @Date 2018-08-02 17:30
 */
public class MapTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.center("bidiMap test start", 31, "="));
        BidiMap bidiMap = new DualHashBidiMap();
        bidiMap.put("BJ", "北京");
        bidiMap.put("SH", "上海");
        bidiMap.put("GZ", "广州");
        bidiMap.put("CD", "成都");
        System.out.println("key-value:" + bidiMap.get("BJ"));
        System.out.println("value-key:" + bidiMap.getKey("上海"));
        bidiMap.put("SD", "成都");
        //value值重复覆盖掉原有的value,也就是说bidiMap的key和value都是唯一不重复的
        System.out.println("value-key:" + bidiMap.getKey("成都"));
        System.out.println(StringUtils.center("bidiMap test end", 31, "="));

        System.out.println(StringUtils.center("multiValueMap test start", 31, "="));
        MultiValueMap multiValueMap = new MultiValueMap();
        multiValueMap.put("java", "zhou");
        multiValueMap.put("java", "zhang");
        multiValueMap.put("java", "wang");
        multiValueMap.put("java", "li");
        multiValueMap.remove("java", "zhou");
        System.out.println("java students: " + multiValueMap.get("java"));
        System.out.println(StringUtils.center("multiValueMap test end", 31, "="));

        System.out.println(StringUtils.center("multiKeyMap test start", 31, "="));
        MultiKeyMap multiKeyMap = new MultiKeyMap();
        multiKeyMap.put("zhou", "ning", "高级工程师");
        multiKeyMap.put("peng", "jiajia", "高级经理");
        System.out.println(multiKeyMap.get("zhou", "ning"));

        System.out.println(StringUtils.center("multiKeyMap test end", 31, "="));
        //map中最近使用或者插入的元素会被保留
        System.out.println(StringUtils.center("lruMap test start", 31, "="));
        LRUMap lruMap = new LRUMap(2);
        lruMap.put("zhou", "ning");
        lruMap.put("peng", "jiajia");
        lruMap.get("zhou");
        lruMap.put("he", "xiaofei");
        System.out.println(lruMap);
        System.out.println(StringUtils.center("lruMap end start", 31, "="));
        //lazyMap未完待续

    }
}
