package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class UpdCardBalanceDTO {

	private Double amount;
	private String cardNumber;
	private String customerCode;
	private String fuelCode;
	private String transactionId;

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
	@Override
	public String toString() {
		return "UpdCardBalanceDTO [amount=" + amount + ", cardNumber="
				+ cardNumber + ", customerCode=" + customerCode + ", fuelCode="
				+ fuelCode + ", transactionId=" + transactionId + "]";
	}
	
}
