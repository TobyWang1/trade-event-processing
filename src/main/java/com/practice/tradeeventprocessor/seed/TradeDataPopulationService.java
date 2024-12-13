package com.practice.tradeeventprocessor.seed;

import com.practice.tradeeventprocessor.model.Trade;
import com.practice.tradeeventprocessor.repository.TradeRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class TradeDataPopulationService {
    private static final Logger LOG = LoggerFactory.getLogger(TradeDataPopulationService.class);
    private final TradeRepository tradeRepository;
    private final Random random  = new Random();
    private static final String[] symbols = {"AAPL", "GOOGL", "MSFT", "TSLA", "AMZN"};
    private static final int NUMBER_OF_TRADES = 100;

    @Autowired
    public TradeDataPopulationService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * This method populates the database with random trade data
     */
    @PostConstruct
    public void populateDatabase() {
        LOG.info("Populating the database with mock trade data");

        for (int i = 0; i < NUMBER_OF_TRADES; i++) {
            Trade trade = createRandomTrade();
            tradeRepository.save(trade);
        }
    }

    /**
     * Generates a random Trade object
     * @return a Trade object filled with random data
     */
    private Trade createRandomTrade() {
        String symbol = symbols[random.nextInt(symbols.length)];
        String action = random.nextBoolean() ? "BUY" : "SELL";
        int quantity = random.nextInt(100) + 1; // Random quantity between 1 and 100
        BigDecimal price = BigDecimal.valueOf(100 + (random.nextDouble() * 900)); // Random price between 100 and 1000

        Trade trade = new Trade();
        trade.setSymbol(symbol);
        trade.setAction(action);
        trade.setQuantity(quantity);
        trade.setPrice(price);
        // tradeTime is automatically set to now
        return trade;
    }
}
