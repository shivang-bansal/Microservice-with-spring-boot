package com.currency.currencyexchangeservice.repository;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeServiceRepository extends JpaRepository<CurrencyExchangeRate,Integer> {

    CurrencyExchangeRate findByFromAndTo(String from, String to);
}
