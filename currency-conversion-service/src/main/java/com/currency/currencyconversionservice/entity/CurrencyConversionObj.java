package com.currency.currencyconversionservice.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter @Setter @NoArgsConstructor
public class CurrencyConversionObj {

    private Integer id;
    private String from;
    private String to;
    private BigDecimal quantity;
    private BigDecimal exchangeRate;
    private BigDecimal calcAmount;

    public CurrencyConversionObj(Integer id, String from, String to, BigDecimal quantity, BigDecimal exchangeRate, BigDecimal calcAmount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.quantity = quantity;
        this.exchangeRate = exchangeRate;
        this.calcAmount = calcAmount;
    }
}
