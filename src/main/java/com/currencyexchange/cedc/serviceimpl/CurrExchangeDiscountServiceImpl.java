package com.currencyexchange.cedc.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.currencyexchange.cedc.dto.BillDetailsDto;
import com.currencyexchange.cedc.dto.ExchangeApiResponse;
import com.currencyexchange.cedc.dto.ItemCategory;
import com.currencyexchange.cedc.dto.ItemDto;
import com.currencyexchange.cedc.dto.UserType;
import com.currencyexchange.cedc.feignclient.CurrencyExchangeClient;
import com.currencyexchange.cedc.service.CurrExchangeDiscountService;

@Service("currExchangeDiscountService")
public class CurrExchangeDiscountServiceImpl implements CurrExchangeDiscountService{
	
	@Autowired
	private CurrencyExchangeClient currencyExchange;
	
	@Value("${exchange.rate.apikey}")
	private String apiKey;

	@Override
	public BigDecimal calculateNetPayableBill(BillDetailsDto billDetailsDto) {
		int discountPercentage = calculateDiscountPercentage(billDetailsDto);
		BigDecimal totalAmountExcludeGrocery = calculateTotalAmountExcludeGrocery(billDetailsDto.getItems());
		BigDecimal totalGroceryAmount = billDetailsDto.getTotalAmount().subtract(totalAmountExcludeGrocery);
		BigDecimal discountedAmount = totalAmountExcludeGrocery
                .multiply(new BigDecimal(discountPercentage)) 
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP); 
		System.out.println("discount amount -----"+discountedAmount);
		//call exchange rate api 
		ExchangeApiResponse apiResponse = currencyExchange.getCurrencyExchangeRates(apiKey, billDetailsDto.getOriginalCurrency());
		BigDecimal amount = totalAmountExcludeGrocery.subtract(discountedAmount).add(totalGroceryAmount);
		if (apiResponse.getResult().equalsIgnoreCase("success")) {
			Double targetCurrencyRate = apiResponse.getConversion_rates().get(billDetailsDto.getTargetCurrency());
			return amount.multiply(new BigDecimal(targetCurrencyRate));
		}
		return amount;
	}
	
	private int calculateDiscountPercentage(BillDetailsDto billDetailsDto) {
		if (billDetailsDto.getUserType().toString()
				.equalsIgnoreCase(UserType.EMPLOYEE.toString())) {
			return 30;
		} else if (billDetailsDto.getUserType().toString()
				.equalsIgnoreCase(UserType.AFFILIATE.toString())) {
			return 10;
		} else if (billDetailsDto.getCustomerTenure() >= 2 
				|| billDetailsDto.getTotalAmount().compareTo(new BigDecimal(100)) > 0 
				|| billDetailsDto.getTotalAmount().compareTo(new BigDecimal(100)) > 1) {
			return 5;
		} else {
			return 0;
		}
	}
	
	private BigDecimal calculateTotalAmountExcludeGrocery(List<ItemDto> items) {
		return items.stream().filter(
				item-> !item.getItemCategory().equals(ItemCategory.GROCERY))
				.map(ItemDto::getItemPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
