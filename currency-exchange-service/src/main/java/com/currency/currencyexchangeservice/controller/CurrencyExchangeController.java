package com.currency.currencyexchangeservice.controller;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchangeRate getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to){
        return exchangeService.findRate(from,to);
    }

    @PostMapping("/rate")
    public boolean addCurrencyExchangeRate(@RequestBody CurrencyExchangeRate currencyExchangeRate){
        boolean flag=exchangeService.addRate(currencyExchangeRate);
        return flag;
    }

    @GetMapping("/load")
    public BatchStatus load() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        return exchangeService.loadDataFromCSVtoDB();
    }

}
