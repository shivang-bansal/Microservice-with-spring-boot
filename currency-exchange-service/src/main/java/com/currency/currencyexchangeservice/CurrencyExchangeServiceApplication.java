package com.currency.currencyexchangeservice;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

	@Autowired
	private ExchangeService exchangeService;

	public void addData(){
		exchangeService.addRate(new CurrencyExchangeRate(1001,"USD","INR", BigDecimal.valueOf(74)));
		exchangeService.addRate(new CurrencyExchangeRate(1002,"USD","JPY", BigDecimal.valueOf(110)));
		exchangeService.addRate(new CurrencyExchangeRate(1003,"USD","EUR", BigDecimal.valueOf(0.83)));
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addData();
	}
}
