package com.currency.currencyexchangeservice.controller;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange-service")
public class CurrencyExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchangeRate getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to){
        return exchangeService.findRate(from,to);
    }

    @PostMapping("/")
    public boolean addCurrencyExchangeRate(@RequestBody CurrencyExchangeRate currencyExchangeRate){
        boolean flag=exchangeService.addRate(currencyExchangeRate);
        return flag;
    }

}
