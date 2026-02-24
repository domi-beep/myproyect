package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class UpdDepartmentBalanceDTO {

	private Double amount;
	private String departmentId;
	private String fuelCode;
	private String transactionId;
	private String balanceType;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getFuelCode() {
		return fuelCode;
	}
	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	@Override
	public String toString() {
		return "UpdDepartmentBalanceDTO [amount=" + amount + ", departmentId="
				+ departmentId + ", fuelCode=" + fuelCode + ", transactionId="
				+ transactionId + ", balanceType=" + balanceType + "]";
	}
	
}
