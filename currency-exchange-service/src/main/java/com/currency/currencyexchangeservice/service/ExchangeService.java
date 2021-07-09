package com.currency.currencyexchangeservice.service;

import com.currency.currencyexchangeservice.entity.CurrencyExchangeRate;
import com.currency.currencyexchangeservice.repository.ExchangeServiceRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeServiceRepository exchangeServiceRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public CurrencyExchangeRate addRate(CurrencyExchangeRate currencyExchangeRate){
        if(findRate(currencyExchangeRate.getFrom(),currencyExchangeRate.getTo())!=null){
            return findRate(currencyExchangeRate.getFrom(),currencyExchangeRate.getTo());
        }
        CurrencyExchangeRate result=exchangeServiceRepository.save(currencyExchangeRate);

        return result;
    }

    public CurrencyExchangeRate findRate(String from, String to){
        CurrencyExchangeRate rate= exchangeServiceRepository.findByFromAndTo(from,to);
        return rate;
    }

    public BatchStatus loadDataFromCSVtoDB() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        Map<String, JobParameter> maps=new HashMap<>();
        maps.put("time",new JobParameter(System.currentTimeMillis()));

        JobParameters parameters=new JobParameters(maps);
        JobExecution jobExecution=jobLauncher.run(job,parameters);

        System.out.println("JobExecution: "+jobExecution.getStatus());

        System.out.println("Batch is Running..");
        while (jobExecution.isRunning()){
            System.out.println("....");
        }
        return jobExecution.getStatus();
    }

}
