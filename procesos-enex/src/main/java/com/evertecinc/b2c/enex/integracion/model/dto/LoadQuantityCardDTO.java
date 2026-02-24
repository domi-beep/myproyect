package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadQuantityCardDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -740009634664897440L;
	private Double quantity;
	private String cardNo;
	private String customerCode;
	private String fuelCode;
	private String fuelType;
	private String unitPrice;
	private String transactionId;

}
