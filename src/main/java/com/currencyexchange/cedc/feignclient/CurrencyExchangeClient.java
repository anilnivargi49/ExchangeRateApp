package com.currencyexchange.cedc.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currencyexchange.cedc.dto.ExchangeApiResponse;

@FeignClient(name = "currencyExchangeClient", url = "https://v6.exchangerate-api.com/v6")
public interface CurrencyExchangeClient {
	
	@GetMapping("/{key}/latest/{sourceCurrency}")
    public ExchangeApiResponse getCurrencyExchangeRates(@PathVariable("key") String apiKey, 
    		@PathVariable("sourceCurrency") String sourceCurrency);

}
