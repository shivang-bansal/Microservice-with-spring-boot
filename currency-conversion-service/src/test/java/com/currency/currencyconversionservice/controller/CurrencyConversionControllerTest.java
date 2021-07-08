package com.currency.currencyconversionservice.controller;

import com.currency.currencyconversionservice.entity.CurrencyConversionObj;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyConversionController.class)
class CurrencyConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplateMock;

    @Test
    public void getCurrencyConversionObj_basic() throws Exception {
        Mockito.when(restTemplateMock.getForEntity("http://exchange-service/api/from/USD/to/INR", CurrencyConversionObj.class))
                .thenReturn(new ResponseEntity<CurrencyConversionObj>(new CurrencyConversionObj(1,"USD","INR",BigDecimal.valueOf(74.00)),HttpStatus.OK));
        RequestBuilder request= MockMvcRequestBuilders
                .get("/api/from/USD/to/INR/10")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(request)
                .andExpect(content().json("{\"id\":1,\"from\":\"USD\",\"to\":\"INR\",\"quantity\":10,\"exchangeRate\":74.00,\"calcAmount\":740.00}"))
                .andReturn();

    }
}