package com.ddy.spide.rabbit_mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitMqApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(500L);
            sender.send();
        }
    }

}
