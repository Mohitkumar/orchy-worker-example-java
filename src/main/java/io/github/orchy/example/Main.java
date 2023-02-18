package io.github.orchy.example;

import io.github.mohitkumar.orchy.worker.RetryPolicy;
import io.github.mohitkumar.orchy.worker.Worker;
import io.github.mohitkumar.orchy.worker.WorkerManager;
import io.github.orchy.example.action.EnhanceDataAction;
import io.github.orchy.example.action.SmsAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

@SpringBootApplication
public class Main implements CommandLineRunner
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private WorkerManager manager;

    @Autowired
    private SmsAction smsAction;

    @Autowired
    private EnhanceDataAction enhanceDataAction;

    private static AtomicLong counter = new AtomicLong(0);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run");
        Function<Map<String, Object>, Map<String, Object>> logAction= (Map<String, Object> input) ->{
            LOGGER.info("output ={}", input);
            System.out.println(counter.incrementAndGet());
            return Collections.emptyMap();
        };

        Worker logWorker = Worker
                .newBuilder()
                .DefaultWorker(logAction,"logAction", 2,100,TimeUnit.MILLISECONDS).build();

        manager.registerWorker(logWorker,10);
        manager.registerWorker(Worker.newBuilder()
                .DefaultWorker(smsAction,"smsAction",2,100,TimeUnit.MILLISECONDS)
                .build(), 10);

        manager.registerWorker(Worker.newBuilder()
                .DefaultWorker(enhanceDataAction,"enhanceData",2,100,TimeUnit.MILLISECONDS)
                        .withRetryCount(3).withRetryPolicy(RetryPolicy.FIXED).withTimeout(100)
                .build(), 10);

        manager.start();
    }
}
