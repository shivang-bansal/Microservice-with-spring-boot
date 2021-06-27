package com.currency.currencyexchangeservice.service;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.repository.ExchangeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeServiceRepository exchangeServiceRepository;

    public boolean addRate(CurrencyExchangeRate currencyExchangeRate){
        exchangeServiceRepository.save(currencyExchangeRate);
        return true;
    }

    public CurrencyExchangeRate findRate(String from, String to){
        return exchangeServiceRepository.findByFromAndTo(from,to);
    }

}
