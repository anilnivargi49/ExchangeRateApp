package com.currencyexchange.cedc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.currencyexchange.cedc.dto.BillDetailsDto;
import com.currencyexchange.cedc.dto.ExchangeApiResponse;
import com.currencyexchange.cedc.dto.UserType;
import com.currencyexchange.cedc.feignclient.CurrencyExchangeClient;
import com.currencyexchange.cedc.serviceimpl.CurrExchangeDiscountServiceImpl;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class CurrExchangeDiscountServiceTest {

	@Mock
	CurrencyExchangeClient currencyExchangeClient;
	
	@InjectMocks
	CurrExchangeDiscountServiceImpl currExchangeDiscountService;
	
	@Test
	public void testExchangeDiscountCalculateWithEmployeeUsertype() {
		BillDetailsDto billDetailsDto = TestMockClass.constructBillObj();
		billDetailsDto.setCustomerTenure(1);
		billDetailsDto.setUserType(UserType.EMPLOYEE);
		//construct exchange api response object
		ExchangeApiResponse exchangeApiResponse = new ExchangeApiResponse();
		Map<String, Double> conversionRates = new HashMap<>();
		conversionRates.put("INR", 86.02);
		exchangeApiResponse.setConversion_rates(conversionRates);
		exchangeApiResponse.setResult("success");
		//mock currency exchange rate api
		when(currencyExchangeClient.getCurrencyExchangeRates(any(), anyString()))
		.thenReturn(exchangeApiResponse);
		
		//call service layer method
		BigDecimal totalNetPayableAmount = currExchangeDiscountService.calculateNetPayableBill(billDetailsDto);
		assertNotNull(totalNetPayableAmount);
		assertEquals(new BigDecimal(1204.28).setScale(4, RoundingMode.HALF_UP), totalNetPayableAmount);
	}
	
	@Test
	public void testExchangeDiscountCalculateWithAffiliateUsertype() {
		BillDetailsDto billDetailsDto = TestMockClass.constructBillObj();
		billDetailsDto.setCustomerTenure(1);
		billDetailsDto.setUserType(UserType.AFFILIATE);
		//construct exchange api response object
		ExchangeApiResponse exchangeApiResponse = new ExchangeApiResponse();
		Map<String, Double> conversionRates = new HashMap<>();
		conversionRates.put("INR", 86.02);
		exchangeApiResponse.setConversion_rates(conversionRates);
		exchangeApiResponse.setResult("success");
		//mock currency exchange rate api
		when(currencyExchangeClient.getCurrencyExchangeRates(any(), anyString()))
		.thenReturn(exchangeApiResponse);
		
		//call service layer method
		BigDecimal totalNetPayableAmount = currExchangeDiscountService.calculateNetPayableBill(billDetailsDto);
		assertNotNull(totalNetPayableAmount);
		assertEquals(new BigDecimal(1548.36).setScale(4, RoundingMode.HALF_UP), totalNetPayableAmount);
	}
	@Test
	public void testExchangeDiscountCalculateWithCustomerTenureUsertype() {
		BillDetailsDto billDetailsDto = TestMockClass.constructBillObj();
		billDetailsDto.setCustomerTenure(3);
		billDetailsDto.setUserType(UserType.CUSTOMER);
		//construct exchange api response object
		ExchangeApiResponse exchangeApiResponse = new ExchangeApiResponse();
		Map<String, Double> conversionRates = new HashMap<>();
		conversionRates.put("INR", 86.02);
		exchangeApiResponse.setConversion_rates(conversionRates);
		exchangeApiResponse.setResult("success");
		//mock currency exchange rate api
		when(currencyExchangeClient.getCurrencyExchangeRates(any(), anyString()))
		.thenReturn(exchangeApiResponse);
		
		//call service layer method
		BigDecimal totalNetPayableAmount = currExchangeDiscountService.calculateNetPayableBill(billDetailsDto);
		assertNotNull(totalNetPayableAmount);
		assertEquals(new BigDecimal(1634.38).setScale(4, RoundingMode.HALF_UP), totalNetPayableAmount);
	}
	
}
