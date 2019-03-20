package com.gysoft.utils.test.threadlocal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 周宁
 * @Date 2018-08-15 15:01
 */
public class CyclicBarrierTest3 {

    static final CyclicBarrier barrier = new CyclicBarrier(4);

    public static void main(String[] args) {
        new PrintThread("A",0).start();
        new PrintThread("B",1).start();
        new PrintThread("C",2).start();
        new PrintThread("D",3).start();
    }

    static class PrintThread extends Thread {
        String printStr;
        Integer numberAwaiting;
        public PrintThread(String printStr, Integer numberAwaiting) {
            this.printStr = printStr;
            this.numberAwaiting = numberAwaiting;
        }

        @Override
        public void run() {
            while (true) {
                if (barrier.getNumberWaiting() == numberAwaiting) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println(printStr);
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
