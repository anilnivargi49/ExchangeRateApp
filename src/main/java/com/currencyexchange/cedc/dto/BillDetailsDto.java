package com.currencyexchange.cedc.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public class BillDetailsDto {
	@NotEmpty
	private List<ItemDto> items;
	@NotNull
	private BigDecimal totalAmount;
	@NotBlank
	private UserType userType;
	private int customerTenure;
	@NotBlank
	private String originalCurrency;
	@NotBlank
	private String targetCurrency;
	public List<ItemDto> getItems() {
		return items;
	}
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public int getCustomerTenure() {
		return customerTenure;
	}
	public void setCustomerTenure(int customerTenure) {
		this.customerTenure = customerTenure;
	}
	public String getOriginalCurrency() {
		return originalCurrency;
	}
	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
}
