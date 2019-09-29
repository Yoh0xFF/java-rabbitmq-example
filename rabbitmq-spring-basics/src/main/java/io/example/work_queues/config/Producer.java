package io.example.work_queues.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collections;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void send() {
        for (int i = 0; i < 10; ++i) {
            String message = "Hello World" + String.join("", Collections.nCopies(nextInt(1, 5), "."));

            this.template.convertAndSend(queue.getName(), message);

            System.out.println("[x] Sent '" + message + "'");
        }
    }
}