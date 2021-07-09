package com.currency.currencyexchangeservice.controller;

import com.currency.currencyexchangeservice.config.MessageConfig;
import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RatePublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/message")
    public String bookOrder(@RequestBody CurrencyExchangeRate rate) {

        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, rate);
        return "Success !!";
    }
}
