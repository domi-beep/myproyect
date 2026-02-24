package com.evertecinc.b2c.enex.audits.exceptions;

@SuppressWarnings("serial")
public class AuditsException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public AuditsException() {
		super();
	}

	public AuditsException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}
	
	public AuditsException(String message) {
        super(message);
    }

    public AuditsException(String message, Throwable cause) {
        super(message, cause);
    }

}
