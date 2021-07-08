package com.currency.currencyconversionservice.controller;

import com.currency.currencyconversionservice.entity.CurrencyConversionObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/")
public class CurrencyConversionController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/from/{from}/to/{to}/{quantity}")
    public CurrencyConversionObj getCurrencyConversionObj(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){

        ResponseEntity<CurrencyConversionObj> responseEntity =
                this.restTemplate.getForEntity("http://exchange-service/api/from/"+from+"/to/"+to,CurrencyConversionObj.class);

        CurrencyConversionObj response=responseEntity.getBody();
        return new CurrencyConversionObj(1,from,to,quantity,
                response.getExchangeRate(),quantity.multiply(response.getExchangeRate()));
    }

}
