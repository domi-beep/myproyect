package com.evertecinc.b2c.enex.client.exceptions;

public class MontoMinimoCompraException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;

	public MontoMinimoCompraException() {
		super();
	}

	public MontoMinimoCompraException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MontoMinimoCompraException(String message, Throwable cause) {
		super(message, cause);
	}

	public MontoMinimoCompraException(String message) {
		super(message);
	}

	public MontoMinimoCompraException(Throwable cause) {
		super(cause);
	}

}
