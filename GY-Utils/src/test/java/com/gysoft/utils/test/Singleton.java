package com.gysoft.utils.test;

/**
 * @author 周宁
 * @Date 2018-07-03 9:21
 */
public class Singleton {
    /**
     * 私有构造方法
     */
    private Singleton(){

    }

    /**
     * 类级的内部类，静态成员式内部类，该类的示例与外部类的实例没有绑定关系
     * 而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM保证线程安全
         */
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}
