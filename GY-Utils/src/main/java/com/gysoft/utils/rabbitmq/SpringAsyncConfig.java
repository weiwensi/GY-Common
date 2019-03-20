package com.gysoft.utils.rabbitmq;

        import com.gysoft.utils.threadpool.ThreadPoolExecutorConfig;
        import com.gysoft.utils.threadpool.ThreadPoolExecutorFactory;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.scheduling.annotation.EnableAsync;

        import java.util.concurrent.Executor;
        import java.util.concurrent.SynchronousQueue;
        import java.util.concurrent.TimeUnit;

/**
 * @author 周宁
 * @Date 2018-09-29 9:35
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig {

    @Bean("logSendExecutor")
    public Executor logSendExecutor() {
        return ThreadPoolExecutorFactory.newInstance(new ThreadPoolExecutorConfig("logSendExecutor",10,200,10L,TimeUnit.SECONDS,new SynchronousQueue<>()));
    }

    @Bean("dynamicSendExecutor")
    public Executor dynamicSendExecutor(){
        return ThreadPoolExecutorFactory.newInstance(new ThreadPoolExecutorConfig("dynamicSendExecutor",10,200,10L,TimeUnit.MILLISECONDS,new SynchronousQueue<>()));
    }
}
