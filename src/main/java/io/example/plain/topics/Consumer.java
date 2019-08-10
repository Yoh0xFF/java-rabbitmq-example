package io.example.plain.topics;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private final static String EXCHANGE_NAME = "topics-queue-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String sysQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(sysQueueName, EXCHANGE_NAME, "sys.*");

        String appQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(appQueueName, EXCHANGE_NAME, "app.*");

        String redQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(redQueueName, EXCHANGE_NAME, "*.red");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + consumerTag + "', '" + delivery.getEnvelope().getRoutingKey() + "' : '" + message + "'");
        };
        channel.basicConsume(sysQueueName, true, "sysQueueName", deliverCallback, consumerTag -> { });
        channel.basicConsume(appQueueName, true, "appQueueName", deliverCallback, consumerTag -> { });
        channel.basicConsume(redQueueName, true, "redQueueName", deliverCallback, consumerTag -> { });
    }
}
