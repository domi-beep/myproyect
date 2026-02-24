package com.evertecinc.b2c.enex.client.exceptions;

public class MailException extends Exception {
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public MailException() {
	}

	public MailException(String arg0) {
		super(arg0);
	}

	public MailException(Throwable arg0) {
		super(arg0);
	}

	public MailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
