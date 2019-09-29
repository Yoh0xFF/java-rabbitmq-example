package io.example.routing.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${app.exchange.routing}")
    private String exchangeName;

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(exchangeName, false, false);
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(DirectExchange direct, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("green");
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding2(DirectExchange direct, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("red");
    }

    @Bean
    public Queue autoDeleteQueue3() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding3(DirectExchange direct, Queue autoDeleteQueue3) {
        return BindingBuilder.bind(autoDeleteQueue3).to(direct).with("red");
    }

    @Bean
    public Producer producer() {
        return new Producer();
    }

    @Bean
    public Consumer consumer() {
        return new Consumer();
    }
}
