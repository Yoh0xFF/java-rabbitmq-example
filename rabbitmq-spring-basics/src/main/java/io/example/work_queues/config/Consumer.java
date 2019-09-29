package io.example.work_queues.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "${app.queue.worker}")
public class Consumer {

    private final int instance;

    public Consumer(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void consume(String message) throws InterruptedException {
        StopWatch watch = new StopWatch();

        watch.start();
        doWork(message);
        watch.stop();

        System.out.println("instance " + this.instance + " [x] Received '" + message + "', Done in " + watch.getTotalTimeSeconds());
    }

    private void doWork(String task) throws InterruptedException {
        for (char c : task.toCharArray()) {
            if (c == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
