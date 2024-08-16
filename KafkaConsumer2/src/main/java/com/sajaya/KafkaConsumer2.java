package com.sajaya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer2.class);

    @KafkaListener(
            topics = "WikiMediaRecentChange",
            groupId = "myGroup2"
    )
    public void consume(String eventMessage){

        LOGGER.info(String.format("consumer 2: Event message received -> %s", eventMessage));
    }
}