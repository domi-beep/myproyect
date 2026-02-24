package com.evertecinc.b2c.enex.client.exceptions;

public class OcException extends Exception{

	public OcException() {
		super();
	}

	public OcException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OcException(String message, Throwable cause) {
		super(message, cause);
	}

	public OcException(String message) {
		super(message);
	}

	public OcException(Throwable cause) {
		super(cause);
	}
	
	

}
