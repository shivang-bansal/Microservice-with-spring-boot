package com.currency.currencyexchangeservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class CurrencyExchangeRate {

    @Id
    private Integer id;

    @Column(name = "exchange_from")
    private String from;
    @Column(name = "exchange_to")
    private String to;
    private BigDecimal exchangeRate;

    public CurrencyExchangeRate(int id, String from, String to, BigDecimal exchangeRate) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.exchangeRate = exchangeRate;
    }
}
