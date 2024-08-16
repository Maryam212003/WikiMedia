package com.sajaya.wikimedia;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikiMediaApplication implements CommandLineRunner {

    @Autowired
    private KafkaTopicCreator kafkaTopicCreator;

    public static void main(String[] args) {
        SpringApplication.run(WikiMediaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        kafkaTopicCreator.createTopic("Topic1", 6, (short) 1);
        kafkaTopicCreator.createTopic("Topic2", 6, (short) 1);
        kafkaTopicCreator.createTopic("Topic3", 6, (short) 1);
    }
}
