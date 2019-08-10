package io.example.plain.publish_subscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class Producer {

    private final static String EXCHANGE_NAME = "pubsub-queue-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            for (int i = 0; i < 10; ++i) {
                String message = random(nextInt(1, 25), true, true);

                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

                System.out.println("[x] Sent '" + message + "'");
            }
        }
    }
}
