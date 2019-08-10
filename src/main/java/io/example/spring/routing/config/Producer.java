package io.example.spring.routing.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void send() {
        for (int i = 0; i < 10; ++i) {
            String route = nextInt(1, 100) % 2 == 0 ? "green" : "red";
            String message = random(nextInt(1, 25), true, true);

            this.template.convertAndSend(direct.getName(), route, message);

            System.out.println("[x] Sent '" + route + "' : '" + message + "'");
        }
    }
}