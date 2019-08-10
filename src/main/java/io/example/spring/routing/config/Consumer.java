package io.example.spring.routing.config;

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

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void receive3(String msg) {
        receive(msg, 3);
    }

    private void receive(String msg, int receiver) {
        System.out.println("instance " + receiver + " [x] Received '" + msg + "'");
    }
}
