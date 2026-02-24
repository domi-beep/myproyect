package com.evertecinc.b2c.enex.model.dto.request;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UpdateCustomerBalanceRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2174576163142248891L;
	
	private String customerCode;
	private String date;
	private String remarks = "";
	private String transactionId;
	private BigDecimal amount;

}
