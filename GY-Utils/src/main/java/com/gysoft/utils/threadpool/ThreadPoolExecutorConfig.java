package com.gysoft.utils.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @author 周宁
 * @Date 2018-09-26 10:22
 */
public class ThreadPoolExecutorConfig {

    /**
     * 线程池中线程名称前缀，方便管理
     */
    private String threadNamePrefix;
    /**
     * 核心线程数，默认情况下核心线程会一直存活，
     * 即使处于闲置状态也不会受存keepAliveTime限制。
     * 除非将allowCoreThreadTimeOut设置为true
     */
    private int corePoolSize;
    /**
     * 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。
     * 当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效
     */
    private int maximumPoolSize;
    /**
     * 非核心线程的闲置超时时间，超过这个时间就会被回收
     */
    private long keepAliveTime;
    /**
     * 指定keepAliveTime的单位，如TimeUnit.SECONDS。
     * 当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
     */
    private TimeUnit unit;
    /**
     * 线程池中的任务队列.
     * 常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * @link{ThreadPoolExecutorTest}创建线程池测试方法
     * @param threadNamePrefix
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     */
    public ThreadPoolExecutorConfig(String threadNamePrefix, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this.threadNamePrefix = threadNamePrefix;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    /**
     * 创建等价于
     * @link{Executors.newSingleThreadExecutor(NamedThreadFactory(threadNamePrefix))}方法创建的线程池
     * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，
     * 那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行
     * @param threadNamePrefix 线程名称前缀
     * @return ThreadPoolExecutorConfig
     */
    public static ThreadPoolExecutorConfig newSingleThreadExecutorConfig(String threadNamePrefix) {
        return new ThreadPoolExecutorConfig(threadNamePrefix, 1, 1,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 创建等价于
     * @link{ExecutorService.newCachedThreadPool(NamedThreadFactory(threadNamePrefix)))}方法创建的线程池
     * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
     * 那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，
     * 线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
     * @param threadNamePrefix 线程名称前缀
     * @return
     */
    public static ThreadPoolExecutorConfig newCachedThreadPoolConfig(String threadNamePrefix) {
        return new ThreadPoolExecutorConfig(threadNamePrefix, 0, Integer.MAX_VALUE,60L,TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    /**
     * 创建等价于
     * @link{Executors.newFixedThreadPool(nThreads,NamedThreadFactory(threadNamePrefix))方法的线程池
     * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，
     * 如果某个线程因为执行异常而结束那么会有一个新的线程来替代它
     * @param threadNamePrefix 线程名称前缀
     * @param nThreads 线程池中线程的最大数量
     * @return
     */
    public static ThreadPoolExecutorConfig newFixedThreadPoolConfig(String threadNamePrefix,int nThreads){
        return new ThreadPoolExecutorConfig(threadNamePrefix, nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 创建根据《Java Concurrency in Practice》书中给出的最优线程池的配置
     * @param threadNamePrefix
     * @param cpuUsage 目标CPU的使用率(估算0<=cpuUsage<=1)
     * @param wcUsage 等待时间与计算时间的比率
     * @return ThreadPoolExecutorConfig
     */
    public static ThreadPoolExecutorConfig newOptimalThreadPoolConfig(String threadNamePrefix, double cpuUsage, double wcUsage){
        int nCpu = Runtime.getRuntime().availableProcessors();
        if(0>cpuUsage||cpuUsage>1){
            throw new IllegalArgumentException("cpuUsage为【0-1】之间的数值");
        }
        return newFixedThreadPoolConfig(threadNamePrefix, (int) (nCpu*cpuUsage*(1+wcUsage)));
    }

}
