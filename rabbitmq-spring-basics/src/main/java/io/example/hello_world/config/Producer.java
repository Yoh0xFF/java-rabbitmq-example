package io.example.hello_world.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 2000, initialDelay = 2000)
    public void send() {
        String message = "Hello World!!!";

        this.template.convertAndSend(queue.getName(), message);

        System.out.println("[x] Sent '" + message + "'");
    }
}