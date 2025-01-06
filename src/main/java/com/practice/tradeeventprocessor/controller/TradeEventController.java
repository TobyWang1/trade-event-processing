package com.practice.tradeeventprocessor.controller;

import com.practice.tradeeventprocessor.model.Trade;
import com.practice.tradeeventprocessor.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeEventController {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeEventController(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the Real-Time Trade Event Processor!";
    }

    @GetMapping("/trades")
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll(); // Fetch all trades from the repository
    }

    @GetMapping("/latest")
    public List<Trade> getTradeRepository() {
        return tradeRepository.findTop10ByOrderByIdDesc();
    }
}
