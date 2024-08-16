package com.sajaya.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class);
    }

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void run(String... args) throws Exception {
        kafkaProducer.sendMessage(Arrays.asList("Topic1", "Topic2", "Topic3"));
    }
}
