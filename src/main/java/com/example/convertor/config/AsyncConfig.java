package com.example.convertor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {
    @Bean
    public TaskExecutor asyncPdf() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        log.info("=================================== Avail Processor : {}", Runtime.getRuntime().availableProcessors());
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setThreadNamePrefix("async-pdf-");
        executor.setQueueCapacity(16);
        executor.initialize();
        return executor;
    }


}
