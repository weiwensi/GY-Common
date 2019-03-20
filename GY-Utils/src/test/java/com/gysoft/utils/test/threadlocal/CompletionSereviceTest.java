package com.gysoft.utils.test.threadlocal;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * @author 周宁
 * @Date 2018-08-23 16:00
 */
public class CompletionSereviceTest {

    static class Task_04 implements Callable<String>{

        private String name;

        public Task_04(String name) {
            this.name = name;
        }

        @Override
        public String call() {
            int sleepTime = new Random().nextInt(1000);

            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 返回给调用者的值
            String str = name + " sleep time:" + sleepTime+"ms";
            System.out.println(name + " finished...");
            return str;
        }
    }

    private static final int POOL_SIZE = 5;

    private static final int TOTAL_TASK = 20;
    //自己写集合实现获取线程池中的任务返回
    private static void testByQueue(){
        ExecutorService pool = Executors.newFixedThreadPool(5);
        BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<>();
        IntStream.range(0,TOTAL_TASK).forEach(value -> {
            Future future = pool.submit(new Task_04("Thread"+value));
            queue.add(future);
        });
        queue.forEach(stringFuture -> {
            try {
                System.out.println("method1:"+queue.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        pool.shutdown();
    }
    //通过completeService获取线程池中返回的结果
    private static void testByCompleteion(){
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CompletionService<String> completionService = new ExecutorCompletionService(pool);
        IntStream.range(0,TOTAL_TASK).forEach(value -> {
            completionService.submit(new Task_04("Thread"+value));
        });
        IntStream.range(0,TOTAL_TASK).forEach(value -> {
            try {
                System.out.println("method2:" + completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        pool.shutdown();


    }
    public static void main(String[] args) {
//        testByQueue();
        testByCompleteion();
    }
}


