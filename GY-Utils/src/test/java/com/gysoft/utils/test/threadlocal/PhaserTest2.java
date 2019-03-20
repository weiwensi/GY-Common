package com.gysoft.utils.test.threadlocal;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 周宁
 * @Date 2018-08-23 15:00
 */
public class PhaserTest2 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        IntStream.range(0,5).forEach(value -> {
            Task_02 task_02 = new Task_02(phaser);
            new Thread(task_02,"PhaseTest_"+value).start();
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arrive();
    }


    static class Task_02 implements Runnable{

        private final Phaser phaser;

        Task_02(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            phaser.awaitAdvance(phaser.getPhase());
            System.out.println(Thread.currentThread().getName()+"执行任务");
        }
    }

}
