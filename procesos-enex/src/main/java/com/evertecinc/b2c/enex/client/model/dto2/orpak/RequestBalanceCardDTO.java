package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;

public class RequestBalanceCardDTO {

	private Long idCard;
	private String cardNumber;
	private BigDecimal remainingAmount;
	/**
	 * @return the remainingAmount
	 */
	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}
	/**
	 * @param remainingAmount the remainingAmount to set
	 */
	public void setRemainingAmount(BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	/**
	 * @return the idCard
	 */
	public Long getIdCard() {
		return idCard;
	}
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestBalanceCardDTO [idCard=" + idCard + ", cardNumber="
				+ cardNumber + "]";
	}
	
}
