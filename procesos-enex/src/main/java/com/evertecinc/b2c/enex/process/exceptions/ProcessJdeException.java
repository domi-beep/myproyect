package com.evertecinc.b2c.enex.process.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessJdeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6621383481474829728L;
	
	private String uri;
	private String returnCode;
	
	
	public ProcessJdeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ProcessJdeException(String message, Throwable cause) {
		super(message, cause);
	}
	public ProcessJdeException(String message) {
		super(message);
	}
	public ProcessJdeException(Throwable cause) {
		super(cause);
	} 
	
	

}
