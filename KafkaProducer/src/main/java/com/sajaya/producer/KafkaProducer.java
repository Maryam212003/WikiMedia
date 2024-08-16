package com.sajaya.producer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(List<String> topics) throws InterruptedException {
        for (String topic : topics) {
            BackgroundEventHandler eventHandler = new KafkaProducerHandler(kafkaTemplate, topic);
            BackgroundEventSource eventSource = new BackgroundEventSource
                    .Builder(
                    eventHandler,
                    new EventSource.Builder(URI.create(url))
            )
                    .build();
            eventSource.start();
            // Optional: add a short delay between sending to different topics if needed
        }

        // Ensure the producer runs for a certain amount of time
        TimeUnit.MINUTES.sleep(10);
    }
}
