package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class DepartmentBalanceDTO {

	private Double balance;
	private String balanceType;
	private String code;
	private String stockType;
	private String description;
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStockType() {
		return stockType;
	}
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "DepartmentBalanceDTO [balance=" + balance + ", balanceType="
				+ balanceType + ", code=" + code + ", stockType=" + stockType
				+ ", description=" + description + "]";
	}

}
