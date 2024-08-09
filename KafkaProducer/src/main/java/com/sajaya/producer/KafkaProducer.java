package com.sajaya.producer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic = "WikiMediaRecentChange";

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        BackgroundEventHandler eventHandler = new KafkaProducerHandler(kafkaTemplate, topic);
        BackgroundEventSource eventSource = new BackgroundEventSource
                .Builder(
                        eventHandler,
                        new EventSource.Builder(URI.create(url)
                        ))
                .build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
