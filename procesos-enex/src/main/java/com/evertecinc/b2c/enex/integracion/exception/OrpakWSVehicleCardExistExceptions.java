package com.evertecinc.b2c.enex.integracion.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrpakWSVehicleCardExistExceptions extends Exception {
	
	public OrpakWSVehicleCardExistExceptions(String string) {
		super(string);
	}
/**
	 * 
	 */
	private static final long serialVersionUID = -4826844195545752182L;
	
	private String uri;
	private String returnCode;

}
