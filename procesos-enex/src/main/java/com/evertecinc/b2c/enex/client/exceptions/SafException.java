package com.evertecinc.b2c.enex.client.exceptions;

public class SafException extends Exception {
	private static final long serialVersionUID = -2447632439538847874L;

	public SafException() {
		super();
	}

	public SafException(String text) {
		super(text);
	}

	public SafException(Throwable throwable) {
		super(throwable);
	}

	public SafException(String text, Throwable throwable) {
		super(text, throwable);
	}

}
