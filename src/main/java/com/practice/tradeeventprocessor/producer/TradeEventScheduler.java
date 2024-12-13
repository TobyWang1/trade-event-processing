package com.practice.tradeeventprocessor.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class TradeEventScheduler {

    private final TradeEventProducerService tradeEventProducerService;

    @Autowired
    public  TradeEventScheduler(TradeEventProducerService tradeEventProducerService) {
        this.tradeEventProducerService = tradeEventProducerService;
    }

    @Scheduled(fixedRate = 5000) // Sends and event every 5 seconds
    public void produce() {
        tradeEventProducerService.sendTradeEvents();
    }
}
