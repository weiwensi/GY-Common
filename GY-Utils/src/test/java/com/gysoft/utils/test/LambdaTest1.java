package com.gysoft.utils.test;

/**
 * @author DJZ-PJJ
 * @Description: TODO
 * @date 2019/1/1414:54
 */
public class LambdaTest1 {

    interface LambdaInterface{
        void dbatest2(String msg);
    }

    public static void main(String[] args) {
        LambdaTest1 test1 = new LambdaTest1();
        LambdaInterface lambdaInterface = (msg) -> System.out.println("pjj say ："+msg);
        lambdaInterface.dbatest2("hello world!");
    }
}
