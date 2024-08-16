package com.sajaya.wikimedia;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KafkaTopicCreator {

    private final KafkaAdmin kafkaAdmin;

    @Autowired
    public KafkaTopicCreator(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    public void createTopic(String topicName, int partitions, short replicationFactor) {
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
            System.out.println("********topic created successfuly***************");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
