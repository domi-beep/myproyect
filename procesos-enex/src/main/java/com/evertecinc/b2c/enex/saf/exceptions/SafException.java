package com.evertecinc.b2c.enex.saf.exceptions;

@SuppressWarnings("serial")
public class SafException extends RuntimeException {
	
	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public SafException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SafException(String message, Throwable cause) {
		super(message, cause);
	}

	public SafException(String message) {
		super(message);
	}

	public SafException() {
		super();
	}

	public SafException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
