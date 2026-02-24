package com.evertecinc.b2c.enex.email.exceptions;

@SuppressWarnings("serial")
public class MailQueueException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public MailQueueException() {
		super();
	}

	public MailQueueException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}
	
	public MailQueueException(String message) {
        super(message);
    }

}
