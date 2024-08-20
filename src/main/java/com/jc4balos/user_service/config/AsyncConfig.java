package com.jc4balos.user_service.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        Dotenv dotEnv = Dotenv.load();
        int corePoolSize = Integer.parseInt(dotEnv.get("THREAD_CORE_POOL_SIZE"));
        int maxPoolSize = Integer.parseInt(dotEnv.get("THREAD_MAX_POOL_SIZE"));
        int queueCapacity = Integer.parseInt(dotEnv.get("THREAD_QUEUE_CAPACITY"));

        // TODO: Make the parameters as environment variables
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("threadNumber-");
        executor.initialize();
        return executor;
    }
}
