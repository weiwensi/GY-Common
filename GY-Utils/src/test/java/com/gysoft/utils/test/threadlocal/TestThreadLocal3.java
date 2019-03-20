package com.gysoft.utils.test.threadlocal;

/**
 * @author 周宁
 * @Date 2018-08-14 14:30
 */
public class TestThreadLocal3 {

    private static NumberList numberList = new NumberList();

    private static ThreadLocal<NumberList> local = ThreadLocal.withInitial(()->numberList);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads=new Thread[3];
        for(int i=0;i<3;i++){
            threads[i]=new Thread(() -> {
                NumberList list=local.get();
                for(int j=0;j<1000;j++){
                    list.init();
                }
                local.set(list);
                System.out.println(Thread.currentThread().getName()+"==="+local.get().num);
                //
            });
        }
        Thread.sleep(1000);
        for(Thread t:threads){
            t.start();
        }
    }

    static class NumberList{
        int num;
        public void init(){
            num++;
        }
    }
}
