package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdCustomerBalanceDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5722201837894892435L;
	
	private String customerCode;
	private LocalDateTime date;
	private String transactionId;
	private BigDecimal amount;
	private String remarks = "";
	

}
