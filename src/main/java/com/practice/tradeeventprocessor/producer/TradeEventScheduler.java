package com.practice.tradeeventprocessor.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TradeEventScheduler {

    private final TradeEventProducerService tradeEventProducerService;

    @Autowired
    public  TradeEventScheduler(TradeEventProducerService tradeEventProducerService) {
        this.tradeEventProducerService = tradeEventProducerService;
    }

    @Scheduled(fixedRate = 10000) // Sends and event every 10 seconds
    public void produce() {
        tradeEventProducerService.sendTradeEvents();
    }
}
