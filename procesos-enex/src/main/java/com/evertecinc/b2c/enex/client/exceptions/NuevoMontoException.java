package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class NuevoMontoException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public NuevoMontoException() {
		super();
	}

	public NuevoMontoException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

	public NuevoMontoException(String string, Exception e) {
		super(string, e);
	}

}