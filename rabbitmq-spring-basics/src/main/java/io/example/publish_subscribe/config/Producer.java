package io.example.publish_subscribe.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void send() {
        for (int i = 0; i < 10; ++i) {
            String message = RandomStringUtils.random(nextInt(1, 25), true, true);

            this.template.convertAndSend(fanout.getName(), "", message);

            System.out.println("[x] Sent '" + message + "'");
        }
    }
}