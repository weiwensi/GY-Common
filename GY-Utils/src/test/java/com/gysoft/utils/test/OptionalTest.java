package com.gysoft.utils.test;

import com.gysoft.bean.utils.Pair;
import com.gysoft.utils.exception.ParamInvalidException;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("mmp");
//        Optional nul = Optional.of(null);
        System.out.print(name.get());
        Optional empty = Optional.ofNullable(null);
        if (name.isPresent()) {
            System.out.println(name.get());
        }
        //执行下面的代码会输出：No value present
        try {
            //在空的Optional实例上调用get()，抛出NoSuchElementException
            System.out.println(empty.get());
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: " + value.length());
        });
        System.out.println(empty.orElse("123"));
        //orElseGet与orElse方法类似，区别在于orElse传入的是默认值，
        //orElseGet可以接受一个lambda表达式生成默认值。
        //输出：Default Value
        System.out.println(empty.orElseGet(() -> "Default Value"));
        //输出：Sanaulla
        System.out.println(name.orElseGet(() -> "Default Value"));

        //orElseThrow与orElse方法类似。与返回默认值不同，
        //orElseThrow会抛出lambda表达式或方法生成的异常

        try {
            empty.orElseThrow(ParamInvalidException::new);
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        //map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
        //为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));
    }
}
