package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class ClientException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public ClientException() {
		super();
	}

	public ClientException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}
	

	public ClientException(String arg0) {
		super(arg0);
	}

	public ClientException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
