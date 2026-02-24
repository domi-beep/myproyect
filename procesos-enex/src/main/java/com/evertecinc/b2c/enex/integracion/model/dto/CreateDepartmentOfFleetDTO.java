package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentOfFleetDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1635285061225939883L;
	
	private String operationtype;
	private String customercode;
	private String deptcode;
	private String stocktype;
	private String stockcode;
	private String balancetype;

}
