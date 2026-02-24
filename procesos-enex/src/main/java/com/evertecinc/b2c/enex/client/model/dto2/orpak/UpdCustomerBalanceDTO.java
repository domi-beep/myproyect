package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;
import java.util.Date;

public class UpdCustomerBalanceDTO {
	
	private String customerCode;
	private Date date;
	private String transactionId;
	private BigDecimal amount;
	private String remarks = "";
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "UpdCustomerBalanceDTO [customerCode=" + customerCode
				+ ", date=" + date + ", transactionId=" + transactionId
				+ ", amount=" + amount + ", remarks=" + remarks + "]";
	}
	
	
	
}
