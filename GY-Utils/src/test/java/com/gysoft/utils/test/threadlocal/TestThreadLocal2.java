package com.gysoft.utils.test.threadlocal;

/**
 * @author 周宁
 * @Date 2018-08-14 14:27
 */
public class TestThreadLocal2 {

    private static Integer a = new Integer(200);
    private static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> {
        return a;//初始值
    });

    public static void main(String[] args) {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(() -> {
                Integer mun = local.get();
                for (int j = 0; j < 100; j++) {
                    mun = mun + 1;
                }
                local.set(mun);
                System.out.println(Thread.currentThread().getName() + "===" + local.get());
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        Integer a = 122;
        Integer b = 122;
        Integer c = 144;
        Integer d = 144;
        System.out.println(a==b);
        System.out.println(c==d);
    }
}
