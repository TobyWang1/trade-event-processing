package com.practice.tradeeventprocessor.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TradeEventConsumerService {
    private static final Logger LOG = LoggerFactory.getLogger(TradeEventConsumerService.class);

    @KafkaListener(topics = "trades", groupId = "trade-event-group")
    public void listen(ConsumerRecord<String, String> record) {
        LOG.info("Received trade event: {}", record.value());
        // Here you could add business logic to process the trade event
        // (e.g., storing it in a database, running calculations, etc.)
    }
}