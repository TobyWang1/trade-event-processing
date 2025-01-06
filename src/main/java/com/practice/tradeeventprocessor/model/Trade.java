package com.practice.tradeeventprocessor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
@Data // This annotation generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
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
    private LocalDateTime tradeTime;

    @PrePersist
    protected void onCreate() {
        this.tradeTime = LocalDateTime.now();
    }
}
