package io.example.spring.topics.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${app.exchange.topics}")
    private String exchangeName;

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(exchangeName, false, false);
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(TopicExchange topic, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("sys.*");
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding2(TopicExchange topic, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("app.*");
    }

    @Bean
    public Queue autoDeleteQueue3() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding3(TopicExchange topic, Queue autoDeleteQueue3) {
        return BindingBuilder.bind(autoDeleteQueue3).to(topic).with("*.red");
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
