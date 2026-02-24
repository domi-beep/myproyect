package com.evertecinc.b2c.enex.keycloak.exceptions;

public class KeycloakUserNotFoundException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public KeycloakUserNotFoundException() {
	}

	public KeycloakUserNotFoundException(String arg0) {
		super(arg0);
	}

	public KeycloakUserNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public KeycloakUserNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
