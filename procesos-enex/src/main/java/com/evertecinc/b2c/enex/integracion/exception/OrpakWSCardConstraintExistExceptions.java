package com.evertecinc.b2c.enex.integracion.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrpakWSCardConstraintExistExceptions extends Exception {/**
	 * 
	 */
	private static final long serialVersionUID = -6923097902131194215L;
	
	private String uri;
	private String returnCode;	
	
	
	public OrpakWSCardConstraintExistExceptions() {
		super();
	}

	public OrpakWSCardConstraintExistExceptions(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrpakWSCardConstraintExistExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public OrpakWSCardConstraintExistExceptions(String message) {
		super(message);
	}

	public OrpakWSCardConstraintExistExceptions(Throwable cause) {
		super(cause);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

}
