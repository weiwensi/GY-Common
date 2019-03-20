package com.gysoft.bean;

import static org.junit.Assert.assertTrue;

import com.gysoft.bean.utils.TypeFunction;
import lombok.Data;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public  void instanceMethod(String[] args) {

        test(weak(Stu::getName), weak(Tea::getAge));
    }
    public  void test(TypeFunction<?,?>...typeFunctions){
    }
    public <R> void testStrong(TypeFunction<?,R> ... typeFunctions){

    }
    @Data
    class Stu{
        private String name;
    }
    @Data
    class Tea{
        private int age;
    }
    public static <T,R>  TypeFunction<?,?> weak(TypeFunction<T,R>  typeFunction){
        return typeFunction;
    }

    public  interface WeakTypeFunction {
       <T,R> TypeFunction<?,?> weak(TypeFunction<T,R> typeFunction);
    }
}
