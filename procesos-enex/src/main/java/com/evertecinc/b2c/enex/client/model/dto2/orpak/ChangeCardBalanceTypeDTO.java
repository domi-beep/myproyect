package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class ChangeCardBalanceTypeDTO {
	
	public enum TypeOfBalance { CARD, DEPARTMENT }
	private String cardNumber;
	private TypeOfBalance balanceType;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public TypeOfBalance getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(TypeOfBalance balanceType) {
		this.balanceType = balanceType;
	}
	
}
