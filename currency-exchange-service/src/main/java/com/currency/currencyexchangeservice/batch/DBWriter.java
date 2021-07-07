package com.currency.currencyexchangeservice.batch;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.repository.ExchangeServiceRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<CurrencyExchangeRate> {

    @Autowired
    private ExchangeServiceRepository repository;

    @Override
    public void write(List<? extends CurrencyExchangeRate> rates) throws Exception {

        System.out.println("Data saved for users"+rates);
        repository.saveAll(rates);
    }
}
