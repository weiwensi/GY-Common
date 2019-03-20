package com.gysoft.utils.test.commons.lang;

import com.gysoft.utils.test.Java8Study;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Range;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 对象工具类测试
 *
 * @author 周宁
 * @Date 2018-07-31 10:50
 */
public class ObjectUtilsTest {

    public static void main(String[] args) throws InterruptedException {
        //参数1为null,用参数2代替
        System.out.println("如果为null设置返回" + ObjectUtils.defaultIfNull(null, "我不是空的"));
        //获取指定参数列表中第一个不为null的元素
        System.out.println("第一个非空元素为" + ObjectUtils.firstNonNull(null, null, "第一个非空字符"));
        //两个参数不相同吧
        System.out.println("参数不相同吧" + ObjectUtils.notEqual("notEqualTest", "notEqualTest"));
        //(多)对象hashcode生成策略
        System.out.println("Java8Study对象的hashCode为" + ObjectUtils.hashCode(new Java8Study()));
        System.out.println("多个Java8Study对象生成的hashCode为" + ObjectUtils.hashCodeMulti(new Java8Study(), new Java8Study()));
        //调用对象的toString方法，如果对象为null则返回空字符串
        System.out.println("null对象的toString方法为空字符串" + ObjectUtils.toString(null));
        //如果第一个参数为null则返回第二个字符串的toString方法
        //ObjectUtils.toString(null, null)           = null
        //ObjectUtils.toString(null, "null")         = "null"
        //ObjectUtils.toString("", "null")           = ""
        //ObjectUtils.toString("bat", "null")        = "bat"
        //ObjectUtils.toString(Boolean.TRUE, "null") = "true"
        System.out.println(ObjectUtils.toString(null, "nullStr"));
        System.out.println(ObjectUtils.toString(new Java8Study(), "nullStr"));
        //获取指定参数列表中最小的元素,value类型需要实现Compareable接口才能调用
        //如果任何对象非null且不相等，则较小的对象
        //如果所有对象都是非null且相等，则第一个
        //如果任何可比数据为空，则为非空对象中的较小者
        //如果所有可比数据都为null，则返回null
        System.out.println("下列元素中最小值" + ObjectUtils.min(1, 2, 3));
        //获取指定参数列表中最大的元素,value类型需要实现Compareable接口才能调用
        System.out.println("下列元素中最大值" + ObjectUtils.max(1, 2, 3));
        //比较两个参数大小，默认null小于任何不为null的值,如果first大于second则返回1否则返回-1,等于零代表相等
        System.out.println("两个元素大小比较" + ObjectUtils.compare(3, 1));
        //参数3设置为true代表null大于任何不为null的值，反之
        System.out.println("连个元素大小比较，第三个参数设置为true则表示null大于任何值" + ObjectUtils.compare(null, 1, true));
        //获取参数列表中最频繁出现的项
        System.out.println("一组元素的众数" + ObjectUtils.mode(1, 2, 3, 3, 4, 5));
        //获取一组元素中数据的中位数
        //median(Comparator<T>,  T...)和median(T...)
        //第一个方法提供了参数列表对象的排序策略，第二个方法采用了Java中Tree的默认排序策略。
        //目的是找出参数序列的中间元素，如果参数序列元素数为偶数个则中间元素取低位的
        System.out.println("一组元素的中位数" + ObjectUtils.median(1, 2, 54, 5, 6));
        //Range使用，Range类，具有范围的对象。适合可比较对象，Range范围不可变，可以存在任意类型对象范围，
        // 范围内的元素类型应该保持一致。
        System.out.println("=================分割线 <Range> 的使用开始");
        Date start = new Date();
        Date tmp = new Date();
        TimeUnit.SECONDS.sleep(1);
        Date end = new Date();
        Range<Date> dateRange = Range.between(start, end);
        System.out.println("tmp是否在range对象范围内?" + dateRange.contains(tmp));
        System.out.println("rang对象的最大边界" + dateRange.getMaximum());
        System.out.println("=================分割线 <Range> 的使用结束");
    }
}
