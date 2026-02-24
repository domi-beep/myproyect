package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;

public class ResponseBalanceClientDTO {

	private String    jdeAccount;
	private BigDecimal  remainingAmount;

	public String getJdeAccount() {
		return jdeAccount;
	}
	public void setJdeAccount(String jdeAccount) {
		this.jdeAccount = jdeAccount;
	}
	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	@Override
	public String toString() {
		return "ResponseBalanceClientDTO [jdeAccount=" + jdeAccount
				+ ", remainingAmount=" + remainingAmount + "]";
	}
	
	
}
