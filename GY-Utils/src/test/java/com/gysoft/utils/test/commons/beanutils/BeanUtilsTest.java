package com.gysoft.utils.test.commons.beanutils;

import com.gysoft.utils.test.commons.beanutils.bean.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周宁
 * @Date 2018-08-06 10:08
 */
public class BeanUtilsTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //属性赋值
        Class clzz = Class.forName("com.gysoft.utils.test.commons.beanutils.bean.Student");
        Object obj = clzz.newInstance();
        ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class);
        BeanUtils.copyProperty(obj, "id", "1");
        BeanUtils.copyProperty(obj, "name", "VN");
        BeanUtils.copyProperty(obj, "age", 22);
        BeanUtils.copyProperty(obj, "classId", "22");
        BeanUtils.copyProperty(obj, "birthday", "1993-08-27");
        System.out.println("copyProperty test:" + obj);

        //对象赋值
        Student s1 = new Student();
        Student s2 = new Student();
        s1.setName("zhouning");
        s1.setId("1");
        s1.setAge(23);
        s1.setBirthday(new Date());
        BeanUtils.copyProperties(s2, s1);
        System.out.println("copyProperties test:" + s2);

        //把一个map集合中的数据拷贝到javaBean中
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "eason");
        map.put("age", 45);
        map.put("classId", 4);
        map.put("birthday", new Date());
        Student s3 = new Student();
        BeanUtils.copyProperties(s3, map);
        System.out.println("map to bean:" + s3);

    }

    /**
     * 使用BeanUtils根据request传递的参数组装为java bean
     *
     * @param request
     * @param clzz
     * @param <T>
     * @return T
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> T requestToBean(HttpServletRequest request, Class<T> clzz) throws InvocationTargetException, IllegalAccessException {
        Object obj = null;
        try {
            obj = clzz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            //获得参数名称
            String name = enu.nextElement();
            //获得参数值
            String value = request.getParameter(name);
            BeanUtils.setProperty(obj, name, value);
        }
        return (T) obj;
    }
}
