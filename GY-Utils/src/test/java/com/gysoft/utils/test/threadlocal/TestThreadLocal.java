package com.gysoft.utils.test.threadlocal;

/**
 * @author 周宁
 * @Date 2018-08-14 11:56
 */
public class TestThreadLocal {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal();

    ThreadLocal<String> stringThreadLocal = new ThreadLocal();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }


    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal testThreadLocal = new TestThreadLocal();
        testThreadLocal.set();
        System.out.println(testThreadLocal.getLong());
        System.out.println(testThreadLocal.getString());

        Thread thread1 = new Thread(() -> {
            testThreadLocal.set();
            System.out.println(testThreadLocal.getLong());
            System.out.println(testThreadLocal.getString());
        });
        thread1.start();
        thread1.join();


    }


}
