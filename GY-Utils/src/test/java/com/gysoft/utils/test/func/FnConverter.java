package com.gysoft.utils.test.func;

/**
 * @author 周宁
 * @Date 2018-11-12 13:56
 */
public class FnConverter {
    public <T>String convertFnToString(Fn<T> fn) {
        return Reflections.fnToFieldName(fn);
    }

    public static void main(String[] args) {
        FnConverter fnConverter = new FnConverter();
        String fieldName = fnConverter.convertFnToString(Foo::getBar);
        System.out.println("方法名：" + fieldName);
    }
}

