package io.github.orchy.example;

import io.github.mohitkumar.orchy.worker.RetryPolicy;
import io.github.mohitkumar.orchy.worker.Worker;
import io.github.mohitkumar.orchy.worker.WorkerManager;
import io.github.orchy.example.action.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main implements CommandLineRunner
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private WorkerManager manager;

    @Autowired
    private SmsAction smsAction;

    @Autowired
    private WhatsappAction whatsappAction;

    @Autowired
    private EmailAction emailAction;

    @Autowired
    private QueryDbAction queryDbAction;

    @Autowired
    private EnhanceDataAction enhanceDataAction;


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        manager.registerWorker(Worker.newBuilder()
                .DefaultWorker(queryDbAction,"query-db",1000,10,TimeUnit.MILLISECONDS)
                .build(), 10);

        manager.registerWorker(Worker.newBuilder()
                .DefaultWorker(smsAction,"sendSms",1000,10,TimeUnit.MILLISECONDS)
                .build(), 10);

        manager.registerWorker(Worker.newBuilder()
                .DefaultWorker(emailAction,"sendEmail",1000,10,TimeUnit.MILLISECONDS)
                        .withRetryCount(3).withRetryPolicy(RetryPolicy.FIXED).withTimeout(5)
                .build(), 10);

        manager.registerWorker(Worker.newBuilder()
                .DefaultWorker(whatsappAction,"sendWahtsapp",1000,10,TimeUnit.MILLISECONDS)
                .withRetryCount(3).withRetryPolicy(RetryPolicy.FIXED).withTimeout(5)
                .build(), 10);

        manager.start();
    }
}
