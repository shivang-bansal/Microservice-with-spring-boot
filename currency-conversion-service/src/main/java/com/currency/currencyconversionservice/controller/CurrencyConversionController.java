package com.currency.currencyconversionservice.controller;

import com.currency.currencyconversionservice.entity.CurrencyConversionObj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/conversion-service")
public class CurrencyConversionController {


    @GetMapping("/from/{from}/to/{to}/{quantity}")
    public CurrencyConversionObj getCurrencyConversionObj(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<CurrencyConversionObj> responseEntity =
                restTemplate.getForEntity("http://localhost:9001/exchange-service/from/"+from+"/to/"+to,CurrencyConversionObj.class);

        CurrencyConversionObj response=responseEntity.getBody();
        return new CurrencyConversionObj(1001,from,to,quantity,
                response.getExchangeRate(),quantity.multiply(response.getExchangeRate()));
    }

}
