package com.gysoft.utils.test;

import com.gysoft.utils.test.transien.bean.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author 周宁
 * @Date 2018-07-24 19:08
 */
public class Java8Study {

    public static void main(String[] args) {
        Function<Integer, Integer> times2 = i -> i  *2;
        Function<Integer, Integer> squared = i -> i*i;
        System.out.println(times2.apply(4));
        System.out.println(squared.apply(4));
        System.out.println(times2.compose(squared).apply(4));
        System.out.println(times2.andThen(squared).apply(4));
        System.out.println(Function.identity().compose(squared).apply(4));   //16

        UnaryOperator<Integer> unaryOperator = UnaryOperator.identity();
        System.out.println(unaryOperator.apply(123));

        System.out.println(getMapByUsername(Arrays.asList("zhou", "peng", "wang"), user -> user.getPassword()));

    }

    public static Map<String,Object> getMapByUsername(List<String> userNames, final Function<User,Object> function){
        List<User> users = new ArrayList(){{
           add(new User("zhou","111"));
            add(new User("peng","123"));
            add(new User("wang","444"));
        }};
        return users.stream().collect(Collectors.toMap(User::getUsername,function));
    }
}
