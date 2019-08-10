package io.example.spring.topics.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String msg) {
        receive(msg, "sys.*");
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String msg) {
        receive(msg, "app.*");
    }

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void receive3(String msg) {
        receive(msg, "*.red");
    }

    private void receive(String msg, String topic) {
        System.out.println("instance " + topic + " [x] Received '" + msg + "'");
    }
}
