package com.currency.currencyexchangeservice;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

	@Autowired
	private ExchangeService exchangeService;

	public void addData(){
		exchangeService.addRate(new CurrencyExchangeRate("USD","INR", BigDecimal.valueOf(74),new Date()));
		exchangeService.addRate(new CurrencyExchangeRate("USD","JPY", BigDecimal.valueOf(110),new Date()));
		exchangeService.addRate(new CurrencyExchangeRate("USD","EUR", BigDecimal.valueOf(0.83),new Date()));
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addData();
	}
}
