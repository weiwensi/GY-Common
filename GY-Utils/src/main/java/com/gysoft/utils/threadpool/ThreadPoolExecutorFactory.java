package com.gysoft.utils.threadpool;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.google.common.collect.Maps;
import com.gysoft.utils.util.EmptyUtils;
import com.gysoft.utils.util.date.MillUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 线程池创建工厂包装
 * @author 周宁
 * @Date 2018-09-26 10:19
 */
@Component
public class ThreadPoolExecutorFactory implements DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolExecutorFactory.class);

    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = (r, e) -> {
        String msg = String.format("%s, Thread pool is EXHAUSTED!" +
                        " Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                        " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                Thread.currentThread().getName(), e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
        LOGGER.error(msg);
        throw new RejectedExecutionException(msg);
    };

    /**
     * 优雅关闭超时时间，单位ms，默认3000ms，超时则强制关闭
     */
    private final long shutdownTimeout = MillUnit.SECOND.getMills()*3;

    private static Map<String, ExecutorService> threadPoolCacheMap = Maps.newConcurrentMap();

    /**
     * 创建用于执行任务的ExecturoService
     *
     * @param threadPoolExecutorConfig
     * @return ExecutorService
     */
    public static ExecutorService newInstance(ThreadPoolExecutorConfig threadPoolExecutorConfig) {
        return threadPoolCacheMap.computeIfAbsent(threadPoolExecutorConfig.getThreadNamePrefix(),
                threadNamePrefix -> new ThreadPoolExecutor(threadPoolExecutorConfig.getCorePoolSize(), threadPoolExecutorConfig.getMaximumPoolSize(), threadPoolExecutorConfig.getKeepAliveTime(), threadPoolExecutorConfig.getUnit()
                        , threadPoolExecutorConfig.getWorkQueue(), new NamedThreadFactory(threadNamePrefix), getRejectedExecutionHandler()));

    }

    /**
     * 当线程池中的资源已经全部使用，添加新线程被拒绝时
     * 会调用RejectedExecutionHandler的rejectedExecution方法
     *
     * @return
     */
    public static RejectedExecutionHandler getRejectedExecutionHandler() {
        return REJECTED_EXECUTION_HANDLER;
    }

    /**
     * 该方法用于优雅关闭所有线程池
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        Set<String> threadNames = threadPoolCacheMap.keySet();
        if (EmptyUtils.isEmpty(threadNames)) {
            return;
        }
        //使用默认线程池来关闭线程池
        ExecutorService shutDownThreadPool = newInstance(ThreadPoolExecutorConfig.newFixedThreadPoolConfig("shutDown",4));
        CountDownLatch latch = new CountDownLatch(threadPoolCacheMap.size());
        for (String name : threadNames) {
            ExecutorService pool = threadPoolCacheMap.get(name);
            ShutDownThreadPoolTask task = new ShutDownThreadPoolTask(pool, latch);
            shutDownThreadPool.submit(task);
        }
        //等所有其他的线程关闭后才关闭默认线程池
        latch.await(10, TimeUnit.SECONDS);
        //关闭默认线程池shutDownThreadPool
        Runtime.getRuntime().addShutdownHook(new ShutDownThreadPoolTask(shutDownThreadPool, null));
    }

    /**
     * 关闭线程池任务
     */
    private class ShutDownThreadPoolTask extends Thread {

        private ExecutorService executorService;

        private CountDownLatch countDownLatch;

        public ShutDownThreadPoolTask(ExecutorService executorService, CountDownLatch countDownLatch) {
            this.executorService = executorService;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                //先尝试软关闭线程池
                executorService.shutdown();
                try {
                    executorService.awaitTermination(shutdownTimeout, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    LOGGER.info("", e);
                    LOGGER.warn("线程池超过{}ms仍未关闭，强制关闭。", shutdownTimeout);
                }
                //超时后仍未关闭则强制关闭线程池
                if (!executorService.isShutdown()) {
                    executorService.shutdownNow();
                }
            } finally {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        }
    }

}
