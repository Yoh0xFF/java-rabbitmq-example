package io.example.work_queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Producer {

    private final static String QUEUE_NAME = "worker-queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();

            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

            for (int i = 0; i < 10; ++i) {
                String message = "Hello World" + String.join("", Collections.nCopies(nextInt(1, 5), "."));
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            }
        }
    }
}
