package io.example.spring.publish_subscribe.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String msg) {
        receive(msg, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String msg) {
        receive(msg, 2);
    }

    private void receive(String msg, int receiver) {
        System.out.println("instance " + receiver + " [x] Received '" + msg + "'");
    }
}
