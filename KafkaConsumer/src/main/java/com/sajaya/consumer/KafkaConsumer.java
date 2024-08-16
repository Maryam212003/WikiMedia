package com.sajaya.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
        private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

        @KafkaListener(
                topics = "WikiMediaRecentChange",
                groupId = "myGroup"
        )
        public void consume(String eventMessage){

            LOGGER.info(String.format("consumer 1: Event message received -> %s", eventMessage));
        }
}