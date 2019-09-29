package io.example.work_queues.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${app.queue.worker}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Producer producer() {
        return new Producer();
    }

    @Bean
    public Consumer consumer1() {
        return new Consumer(1);
    }

    @Bean
    public Consumer consumer2() {
        return new Consumer(2);
    }

    @Bean
    public Consumer consumer3() {
        return new Consumer(3);
    }
}
