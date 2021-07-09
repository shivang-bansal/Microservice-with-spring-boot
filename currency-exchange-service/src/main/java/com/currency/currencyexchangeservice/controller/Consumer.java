package com.currency.currencyexchangeservice.controller;

import com.currency.currencyexchangeservice.config.MessageConfig;
import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private ExchangeService exchangeService;

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(CurrencyExchangeRate rate) {
        System.out.println("Message received from queue : " + rate);
        exchangeService.addRate(rate);
    }
}
