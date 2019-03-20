package com.gysoft.bean.syslog;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 周宁
 * @Date 2018-06-20 11:01
 */
public interface AbstractSysLogUtil {

    /**
     * 冒号分隔符
     */
    String COLON = ":";

    /**
     * 获取添加操作描述
     *
     * @param operateName
     * @param object      被操作对象
     * @return String
     */
    default String getAddOperDesc(String operateName, String object) {
        return getAddOperDesc(operateName, Collections.singletonList(object));
    }

    default String getAddOperDesc(String operateName, Collection<String> objects) {
        if (objects == null || objects.size() < 1) {
            return null;
        }
        return operateName + COLON + objects.stream().collect(Collectors.joining(","));
    }

    /**
     * 获取删除操作描述
     *
     * @param operateName
     * @param object
     * @return String
     */
    default String getDelOperDesc(String operateName, String object) {
        return this.getDelOperDesc(operateName, Collections.singletonList(object));
    }

    default String getDelOperDesc(String operateName, Collection<String> objects) {
        if (objects == null || objects.size() < 1) {
            return null;
        }
        return operateName + COLON + objects.stream().collect(Collectors.joining(","));
    }

    /**
     * 获取修改操作描述
     *
     * @param operateName
     * @param object
     * @return String
     */
    default String getModOperDesc(String operateName, String object) {
        return operateName + COLON + object;
    }

    /**
     * 获取修改操作描述 a修改为b
     *
     * @param operateName
     * @param preObject
     * @param afterObject
     * @return String
     */
    default String getModDesc(String operateName, String preObject, String afterObject) {
        if (preObject.equals(afterObject)) {
            return getModOperDesc(operateName, preObject);
        }
        return operateName + COLON + preObject + " 修改为 " + afterObject;
    }

    /**
     * 获取自定义操作描述
     *
     * @param operateName
     * @param objects
     * @return String
     */
    default String getCustomDesc(String operateName, Collection<String> objects) throws Exception {
        throw new Exception("提示：请实现自定义操作说明");
    }

    default String getCustomDesc(String operateName, String... objects)throws Exception{
        throw new Exception("提示：请实现自定义操作说明");
    }

    /**
     * 获取操作功能
     *
     * @param
     * @return String
     * @throws Exception
     * @author 周宁
     * @version 1.0
     */
    String getOperFunc() throws Exception;
}
