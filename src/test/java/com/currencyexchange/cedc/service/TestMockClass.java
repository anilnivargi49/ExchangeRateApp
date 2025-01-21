package com.currencyexchange.cedc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;

import com.currencyexchange.cedc.dto.BillDetailsDto;
import com.currencyexchange.cedc.dto.ItemCategory;
import com.currencyexchange.cedc.dto.ItemDto;

public class TestMockClass {
	
	private static final String ITEM_NAME = "Wheat atta";
	private static final BigDecimal TOTAL_AMOUNT = new BigDecimal(20.00);
	private static final BigDecimal ITEM_PRICE = new BigDecimal(20.00);
	private static final String ORIGINAL_CURRENCY = "INR";
	private static final String TARGET_CURRENCY = "INR";

	public static BillDetailsDto constructBillObj() {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemName(ITEM_NAME);
		itemDto.setItemCategory(ItemCategory.OTHER);
		itemDto.setItemPrice(ITEM_PRICE);
		List<ItemDto> listItems = new ArrayList<>();
		listItems.add(itemDto);
		BillDetailsDto billDetailsDto = new BillDetailsDto();
		billDetailsDto.setItems(listItems);
		billDetailsDto.setTotalAmount(TOTAL_AMOUNT);
		billDetailsDto.setOriginalCurrency(ORIGINAL_CURRENCY);
		billDetailsDto.setTargetCurrency(TARGET_CURRENCY);
		return billDetailsDto;
	}
	

}
