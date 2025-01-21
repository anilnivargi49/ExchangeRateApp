package com.currencyexchange.cedc.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchange.cedc.dto.BillDetailsDto;
import com.currencyexchange.cedc.service.CurrExchangeDiscountService;

@RestController
@RequestMapping("/exchangerate")
public class CurrExchangeDiscountController {

	@Autowired
	private CurrExchangeDiscountService currExchangeDiscountService;
	
	@PostMapping("/calculate")
	public ResponseEntity<BigDecimal> getCurrencyExchangeWithDiscount(
			@RequestBody BillDetailsDto billDetailsDto){
		return new ResponseEntity<BigDecimal>(
				currExchangeDiscountService.calculateNetPayableBill(billDetailsDto), HttpStatus.OK);
	}
}
