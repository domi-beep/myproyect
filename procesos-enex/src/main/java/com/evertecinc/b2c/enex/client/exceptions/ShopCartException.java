package com.evertecinc.b2c.enex.client.exceptions;

public class ShopCartException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public ShopCartException() {
	}

	public ShopCartException(String arg0) {
		super(arg0);
	}

	public ShopCartException(Throwable arg0) {
		super(arg0);
	}

	public ShopCartException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
