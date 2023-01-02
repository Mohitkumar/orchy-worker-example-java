package io.github.orchy.example.config;

import io.github.mohitkumar.orchy.worker.WorkerManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Value("${orchy.server.host}")
    private String orchyHost;

    @Value("${orchy.server.port}")
    private int port;

    @Bean
    public WorkerManager getWorkerManager(){
        return new WorkerManager(orchyHost, port);
    }
}
