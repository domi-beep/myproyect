package com.evertecinc.b2c.enex.integracion.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrpakWSTrxDepartmentBalanceExistExceptions extends Exception {/**
	 * 
	 */
	private static final long serialVersionUID = 7832609112030210385L;
	
	private String uri;
	private String returnCode;	

}
