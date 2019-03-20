package com.gysoft.utils.test.threadlocal;

import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/**
 * @author 周宁
 * @Date 2018-08-23 14:55
 */
public class PhaserTest {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        IntStream.range(0,5).forEach(value -> {
            Task_01 task_01 = new Task_01(phaser);
            new Thread(task_01,"PhaseTest_"+value).start();
        });
    }

    static class Task_01 implements Runnable{

        private final Phaser phaser;

        public Task_01(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"执行任务完成，等待其他任务执行");
            //等待其他任务完成
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"继续执行任务");
        }
    }
}
