package com.sajaya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer3.class);

    @KafkaListener(
            topics = "WikiMediaRecentChange",
            groupId = "myGroup3"
    )
    public void consume(String eventMessage){

        LOGGER.info(String.format("consumer 3: Event message received -> %s", eventMessage));
    }
}