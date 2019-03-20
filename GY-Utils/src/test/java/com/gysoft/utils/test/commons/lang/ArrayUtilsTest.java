package com.gysoft.utils.test.commons.lang;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类测试
 *
 * @author 周宁
 * @Date 2018-07-27 16:54
 */
public class ArrayUtilsTest {

    private static final String[] arr = new String[]{"a", "b", "c", "d", "e", "1", "2", "3", "4", "5"};

    public static void main(String[] args) {
        //将arr数组转换为{a,b,c}格式字符串
        System.out.println("数组字符串为:" + ArrayUtils.toString(arr));
        //将arr数组为{a,b,c}格式的综费出家门,如果为null则返回{a,s,d}
        System.out.println("数组字符串为:" + ArrayUtils.toString(null, "{a,s,d}"));
        //将二维数组转换为map集合
        System.out.println("二维数组转换为的map:" + ArrayUtils.toMap(new String[][]{{"red", "##00"}, {"black", "###"}, {"white", "#000"}}));
        //将可变长数据转换为数组
        System.out.println("可变长数据转换成数组:" + printArr(ArrayUtils.toArray(1, 2, 3, 4)));

        //数组为null返回空数组
        System.out.println("数组为null返回空的数组" + ArrayUtils.nullToEmpty(new String[]{}));
        //截取数组，返回新数组，旧数组保持不变
        System.out.println("截取后新的数组" + printArr(ArrayUtils.subarray(arr, 0, 7)));
        //获取数组 长度
        System.out.println("数组长度为" + ArrayUtils.getLength(arr));
        //返回元素在数组中的下标位置不存在则返回-1
        System.out.println("5在数组arr位置为" + ArrayUtils.indexOf(arr, "5"));
        //从给定开始位置查找元素不存在返回-1
        System.out.println("从arr数组的下标为2开始查找5" + ArrayUtils.indexOf(arr, "5", 2));
        //数组是否包含元素，存在为true不存在则为false
        System.out.println("5是否包含在arr数组" + ArrayUtils.contains(arr, "5"));
        //int[]转换成Integer[]
        int[] intArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Integer[] integerArr = ArrayUtils.toObject(intArr);
        //Integer[]转换成int[]
        int[] intArr2 = ArrayUtils.toPrimitive(integerArr);
        System.out.println(intArr);
        System.out.println(integerArr);
        System.out.println(intArr2);
        //数组是为空
        System.out.println("数组为空:" + ArrayUtils.isEmpty(arr));
        //数组不空
        System.out.println("数组不为空" + ArrayUtils.isNotEmpty(arr));
        //向数组中添加元素，返回新数组，旧数组保持不变
        System.out.println("往arr数组中添加元素" + printArr(ArrayUtils.add(arr, "6")));
        System.out.println("往arr数组中批量添加元素" + printArr(ArrayUtils.addAll(arr, "6", "7", "8", "9", "0")));
        //在数组的指定下标位置添加新元素,旧数组保持不变数组下标不能用户越界，返回新数组，旧数组保持不变
        System.out.println("往arr数组下标为1的位置添加元素2" + printArr(ArrayUtils.add(arr, 1, "2")));
        //删除数组指定下标位置的元素，数组下标不能用户越界，返回新数组，旧数组保持不变
        System.out.println("删除arr数组中下标为5的元素" + printArr(ArrayUtils.remove(arr, 5)));
        //删除数组中指定元素，返回新数组，旧数组保持不变
        System.out.println("从arr数组中删除元素1" + printArr(ArrayUtils.removeElement(arr, "1")));
        //批量删除指定下标元素，下标不能越界，返回新数组，旧数组保持不变
        System.out.println("从arr数组中批量删除指定下标的元素" + printArr(ArrayUtils.removeAll(arr, 0, 1, 2, 3, 4, 5)));
        //批量删除指定元素，返回新数组，旧数组保持不变
        System.out.println("从arr数组批量删除元素" + printArr(ArrayUtils.removeElements(arr, "a", "b", "c", "d", "e", "1")));
        //倒叙arr
        ArrayUtils.reverse(arr);
        System.out.println("翻转后的arr数组为" + printArr(arr));
    }

    private static String printArr(Object[] objects) {
        return ArrayUtils.toString(objects);
    }
}
