package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdDepartmentBalanceDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7413942686553904962L;
	private Double amount;
	private String departmentId;
	private String fuelCode;
	private String transactionId;
	private String balanceType;

}
