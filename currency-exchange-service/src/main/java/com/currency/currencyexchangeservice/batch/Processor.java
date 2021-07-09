package com.currency.currencyexchangeservice.batch;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Processor implements ItemProcessor<CurrencyExchangeRate,CurrencyExchangeRate> {

    @Override
    public CurrencyExchangeRate process(CurrencyExchangeRate currencyExchangeRate) throws Exception {

        return currencyExchangeRate;
    }
}
