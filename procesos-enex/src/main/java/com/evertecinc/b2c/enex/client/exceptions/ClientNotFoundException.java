package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class ClientNotFoundException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public ClientNotFoundException() {
		super();
	}

	public ClientNotFoundException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
