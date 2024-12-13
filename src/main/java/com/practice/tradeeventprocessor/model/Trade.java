package com.practice.tradeeventprocessor.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "trades")
@Data // This annotation generates getters, setters, toString, equals, and hashCode
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "trade_time", nullable = false)
    private final LocalDateTime tradeTime = LocalDateTime.now();
}
