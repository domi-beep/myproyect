package com.evertecinc.b2c.enex.process.jde.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessJDEException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6621383481474829728L;
	
	private String uri;
	private String returnCode;
	
	
	public ProcessJDEException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ProcessJDEException(String message, Throwable cause) {
		super(message, cause);
	}
	public ProcessJDEException(String message) {
		super(message);
	}
	public ProcessJDEException(Throwable cause) {
		super(cause);
	} 
	
	

}
