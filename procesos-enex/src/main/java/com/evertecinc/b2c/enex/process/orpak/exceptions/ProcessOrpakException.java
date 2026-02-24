package com.evertecinc.b2c.enex.process.orpak.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrpakException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6621383481474829728L;
	
	private String uri;
	private String returnCode;
	
	
	public ProcessOrpakException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ProcessOrpakException(String message, Throwable cause) {
		super(message, cause);
	}
	public ProcessOrpakException(String message) {
		super(message);
	}
	public ProcessOrpakException(Throwable cause) {
		super(cause);
	} 
	
	

}
