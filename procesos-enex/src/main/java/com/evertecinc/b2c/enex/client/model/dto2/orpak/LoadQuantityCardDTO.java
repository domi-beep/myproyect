package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class LoadQuantityCardDTO {

	private Double quantity;
	private String cardNo;
	private String customerCode;
	private String fuelCode;
	private String fuelType;
	private String unitPrice;
	private String transactionId;
	
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@Override
	public String toString() {
		return "LoadQuantityCardDTO [quantity=" + quantity + ", cardNo="
				+ cardNo + ", customerCode=" + customerCode + ", fuelCode="
				+ fuelCode + ", fuelType=" + fuelType + ", unitPrice="
				+ unitPrice + ", transactionId=" + transactionId + "]";
	}
	
}
