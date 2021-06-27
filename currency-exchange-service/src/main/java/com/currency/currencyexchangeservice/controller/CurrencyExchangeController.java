package com.currency.currencyexchangeservice.controller;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/exchange-service/from/{from}/to/{to}")
    public CurrencyExchangeRate getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to){
        return exchangeService.findRate(from,to);
    }

}
