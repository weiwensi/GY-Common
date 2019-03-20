package com.gysoft.utils.test.commons.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-08-01 8:52
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        //判断字符序列是否为null,或者长度为0
        System.out.println(StringUtils.isEmpty(""));
        //判读字符序列是否为null,或者长度为0，或者为空字符
        System.out.println(StringUtils.isBlank(" "));
        //去除字符串前后空字符方法
        System.out.println(StringUtils.trim(" 你好 思密达    "));
        //去除字符串前后空字符方法
        System.out.println(StringUtils.strip(" hello world      "));
        //比较连个字符序列是否相同
        System.out.println(StringUtils.equals("ased","ased"));

        final String toSubStr = "1234567890qwerty";
        /**截取字符串演示**/
        System.out.println("========截取字符串演示开始=======");
        System.out.println("从字符串的第四位截取后的字符串为"+StringUtils.substring(toSubStr,4));
        System.out.println("从字符串的左侧截取4为长的字符串"+StringUtils.left(toSubStr,4));
        System.out.println("从字符串的右侧截取4为长的字符串"+StringUtils.right(toSubStr,4));
        System.out.println("从字符串的中间第三位截取长度为5的字符串"+StringUtils.mid(toSubStr,3,5));
        //获取第一个参数中，左右都是第二个参数的字符串
        System.out.println("获取第一个参数中，左右都是第二个参数的字符串"+StringUtils.substringBetween("|呵呵哒|","|"));
        System.out.println("========截取字符串演示截取字符串=======");
        //指定字符串来分割字符串为字符串数组
        System.out.println("按照,拆分字符串"+ArrayUtils.toString(StringUtils.split("test,asd,fgh")));
        //连接数组元素为字符串
        System.out.println("连接字符串"+StringUtils.join("hello"," ","zhou","ning"));
        //使用特定字符连接字符串
        System.out.println("连接后的字符串为"+StringUtils.join(new String[]{"hello","world"},'-'));
        //重复指定字符串
        System.out.println("让字符串重复指定次数"+StringUtils.repeat("asd",5));
        //反转字符串
        System.out.println("反转后的字符串为"+StringUtils.reverse("uoy evol i"));
        //在字符串左右拼接字符串
        System.out.println("字符串左右都拼接'='字符使字符长度变为10"+StringUtils.center("test",10,"="));

        String[] arrs = StringUtils.splitByWholeSeparatorPreserveAllTokens("6$&#$3$&#$0$&#$阶段化$&#$1$&#$创建的阶段$&#$2$&#$新构造$&#$3$&#$0$&#$阶段化$&#$1$&#$拆除的阶段$&#$2$&#$无$&#$4$&#$0$&#$尺寸标注$&#$1$&#$长度$&#$2$&#$2200$&#$3$&#$毫米$&#$3$&#$0$&#$标识数据$&#$1$&#$图像$&#$2$&#$$&#$3$&#$0$&#$标识数据$&#$1$&#$注释$&#$2$&#$$&#$3$&#$0$&#$标识数据$&#$1$&#$标记$&#$2$&#$","$&#$");
        System.out.println(arrs.length);
        System.out.println("按照$&#$拆分字符串"+ArrayUtils.toString(StringUtils.splitByWholeSeparatorPreserveAllTokens("6$&#$3$&#$0$&#$阶段化$&#$1$&#$创建的阶段$&#$2$&#$新构造$&#$3$&#$0$&#$阶段化$&#$1$&#$拆除的阶段$&#$2$&#$无$&#$4$&#$0$&#$尺寸标注$&#$1$&#$长度$&#$2$&#$2200$&#$3$&#$毫米$&#$3$&#$0$&#$标识数据$&#$1$&#$图像$&#$2$&#$$&#$3$&#$0$&#$标识数据$&#$1$&#$注释$&#$2$&#$$&#$3$&#$0$&#$标识数据$&#$1$&#$标记$&#$2$&#$","$&#$")));

    }


}
