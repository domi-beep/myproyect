package com.evertecinc.b2c.enex.client.exceptions;

public class CreditCardException extends Exception{
	
	private static final long serialVersionUID = -6340607844054298787L;

	public CreditCardException() {
	}

	public CreditCardException(String arg0) {
		super(arg0);
	}

	public CreditCardException(Throwable arg0) {
		super(arg0);
	}

	public CreditCardException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
