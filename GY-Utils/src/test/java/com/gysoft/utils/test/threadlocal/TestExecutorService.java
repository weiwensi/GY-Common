package com.gysoft.utils.test.threadlocal;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author 周宁
 * @Date 2018-08-14 11:45
 */
public class TestExecutorService {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        Collection<String> sets = Collections.synchronizedCollection(new HashSet<String>());
        IntStream.range(0,10000).forEach(value -> executorService.submit(new Runnable() {
            @Override
            public void run() {
                sets.add(Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }));
        countDownLatch.await();
        executorService.shutdown();

        System.out.println(sets.size());
    }
}
