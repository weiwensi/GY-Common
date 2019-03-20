package com.gysoft.utils.test.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 周宁
 * @Date 2018-08-15 15:20
 */
public class SemaPhoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<THREAD_COUNT;i++){
            final String test = String.valueOf(i);
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("save data="+test+":::" + Thread.currentThread().getName());
                    Thread.currentThread().sleep(5000);
                    semaphore.release();
                } catch (InterruptedException e) {

                }
            });
        }
        threadPool.shutdown();
    }
}
