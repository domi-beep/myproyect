package com.evertecinc.b2c.enex.client.exceptions;

public class ParametersException extends Exception{

	public ParametersException() {
		super();
	}

	public ParametersException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParametersException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParametersException(String message) {
		super(message);
	}

	public ParametersException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
