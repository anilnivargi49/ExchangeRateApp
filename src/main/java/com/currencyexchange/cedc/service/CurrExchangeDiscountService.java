package com.currencyexchange.cedc.service;

import java.math.BigDecimal;

import com.currencyexchange.cedc.dto.BillDetailsDto;

public interface CurrExchangeDiscountService {
	public BigDecimal calculateNetPayableBill(BillDetailsDto billDetailsDto);
}
