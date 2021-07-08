package com.currency.currencyexchangeservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class CurrencyExchangeRate {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "exchange_from")
    private String from;
    @Column(name = "exchange_to")
    private String to;
    @Column(name="created_time")
    private Date time;
    private BigDecimal exchangeRate;

    public CurrencyExchangeRate( String from, String to, BigDecimal exchangeRate,Date time) {
        this.from = from;
        this.to = to;
        this.exchangeRate = exchangeRate;
        this.time=time;
    }

    public CurrencyExchangeRate(Integer id, String from, String to, BigDecimal exchangeRate) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.exchangeRate = exchangeRate;
    }
}
