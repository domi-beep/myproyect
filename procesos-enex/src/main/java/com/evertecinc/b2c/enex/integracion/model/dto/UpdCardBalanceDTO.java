package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdCardBalanceDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6086441348672466724L;
	private Double amount;
	private String cardNumber;
	private String customerCode;
	private String fuelCode;
	private String transactionId;

}
