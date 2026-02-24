package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class ClientServiceException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public ClientServiceException() {
		super();
	}

	public ClientServiceException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

	public ClientServiceException(String string) {
		super(string);
	}

}
