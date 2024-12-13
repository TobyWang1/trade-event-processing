package com.practice.tradeeventprocessor.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.tradeeventprocessor.model.Trade;
import com.practice.tradeeventprocessor.repository.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeEventProducerService {
    private static final Logger LOG = LoggerFactory.getLogger(TradeEventProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TradeRepository tradeRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TradeEventProducerService(KafkaTemplate<String, String> kafkaTemplate,
                                     TradeRepository tradeRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.tradeRepository = tradeRepository;
    }

    /**
     * This method retrieves the latest trades from the database
     * and sends them to Kafka.
     */
    @Transactional
    public void sendTradeEvents() {
        List<Trade> latestTrades = tradeRepository.findTop10ByOrderByIdDesc();
        for (Trade trade : latestTrades) {
            try {
                String event = objectMapper.writeValueAsString(trade);
                kafkaTemplate.send("trades", event);
                LOG.info("Sent trade event: {}", event);
            } catch (Exception e) {
                LOG.error("Error sending trade event: {}", e.getMessage(), e);
            }
        }
    }
}