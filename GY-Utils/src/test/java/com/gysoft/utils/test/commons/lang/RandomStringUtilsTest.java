package com.gysoft.utils.test.commons.lang;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成随机的字符串序列测试
 * @author 周宁
 * @Date 2018-08-01 9:36
 */
public class RandomStringUtilsTest {

    public static void main(String[] args) {
        //随机生成指定长度的字符串，字符来自所有字符
        System.out.println("随机生成字符串为所有字符长度为6的字符串"+RandomStringUtils.random(6));
        //随机生成指定长度的字符串，字符ASCII码范围[37,127）
        System.out.println("随机生成字符全部为字符ASCII范围内的长度为6的字符串"+RandomStringUtils.randomAscii(6));
        //随机生成指定长度的字符串，字符为字母
        System.out.println("随机生成字符全部为字母长度为6的字符串"+RandomStringUtils.randomAlphabetic(6));
        //随机生成指定长度的字符串，字符为字母和数字
        System.out.println("随机生成字符全部为字母和数字的长度为6的字符串"+RandomStringUtils.randomAlphanumeric(6));
        //随机生成指定长度的字符串，字符为数字
        System.out.println("随机生成字符全部为数字的长度为6的字符串"+RandomStringUtils.randomNumeric(6));
        //第一个参数为长度，第二个参数是否包含字母，第三个参数是否包含数字
        System.out.println(RandomStringUtils.random(6,true,false));
        //随机生成指定长度的字符串，字符为第二个参数中字符串中的字符,类似于排列和一样！
        System.out.println("生成一个长度为6的字符串且字符全部为第二个字符串中的字符"+RandomStringUtils.random(6,"asdas1231"));
    }
}
