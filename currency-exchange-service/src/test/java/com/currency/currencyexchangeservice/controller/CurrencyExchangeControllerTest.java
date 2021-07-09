package com.currency.currencyexchangeservice.controller;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyExchangeController.class)
class CurrencyExchangeControllerTest {

    @MockBean
    ExchangeService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getCurrencyExchangeRate() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders
                .get("/api/from/USD/to/INR")
                .accept(MediaType.APPLICATION_JSON);

        Mockito.when(service.findRate("USD","INR")).thenReturn(new CurrencyExchangeRate(1,"USD","INR", BigDecimal.valueOf(74.00)));

        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"from\":\"USD\",\"to\":\"INR\",\"exchangeRate\":74.00}"))
                .andReturn();
    }

    @Test
    void addCurrencyExchangeRate() throws Exception {
        CurrencyExchangeRate rate=new CurrencyExchangeRate(1,"USD","INR", BigDecimal.valueOf(74.00));
        RequestBuilder request= MockMvcRequestBuilders
                .post("/api/rate")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(rate))
                .contentType(MediaType.APPLICATION_JSON);


        Mockito.when(service.addRate(rate)).thenReturn(rate);

        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"from\":\"USD\",\"to\":\"INR\",\"exchangeRate\":74.00}"))
                .andReturn();
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}