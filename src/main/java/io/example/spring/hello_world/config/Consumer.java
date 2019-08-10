package io.example.spring.hello_world.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello-queue")
public class Consumer {

    @RabbitHandler
    public void consume(String message) {
        System.out.println("[x] Received '" + message + "'");
    }
}
