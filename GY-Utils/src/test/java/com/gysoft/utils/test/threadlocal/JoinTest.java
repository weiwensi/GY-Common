package com.gysoft.utils.test.threadlocal;

/**
 * @author 周宁
 * @Date 2018-08-14 18:56
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadJoinTest threadJoinTest1 = new ThreadJoinTest("晓明");
        ThreadJoinTest threadJoinTest2 = new ThreadJoinTest("晓东");
        threadJoinTest1.start();
        /**
         * join的意思是使得放弃当前线程的执行，并返回对应的线程
         *程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         *所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         */
        threadJoinTest1.join(0);
        threadJoinTest2.start();
    }

    static class ThreadJoinTest extends Thread {
        public ThreadJoinTest(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.println(this.getName() + ":" + i);
            }
        }
    }
}
