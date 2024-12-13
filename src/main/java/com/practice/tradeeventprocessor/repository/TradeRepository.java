package com.practice.tradeeventprocessor.repository;

import com.practice.tradeeventprocessor.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findTop10ByOrderByIdDesc();
}
