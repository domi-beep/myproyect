package com.evertecinc.b2c.enex.audits.exceptions;

@SuppressWarnings("serial")
public class AuditNotFoundException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public AuditNotFoundException() {
		super();
	}

	public AuditNotFoundException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}
	
	public AuditNotFoundException(String message) {
        super(message);
    }

}
