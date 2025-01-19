package com.currencyexchange.cedc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.currencyexchange.cedc.feignclient")
public class CurrencyExchangeDiscountCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeDiscountCalculatorApplication.class, args);
	}

}
