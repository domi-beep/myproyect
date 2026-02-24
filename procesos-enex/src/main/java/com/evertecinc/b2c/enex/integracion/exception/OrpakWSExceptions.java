package com.evertecinc.b2c.enex.integracion.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrpakWSExceptions extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6664724419773807250L;

	private static final String ERROR_MESSAGE = "Error al realizar operación";
	
	private String uri;
	private String returnCode;

	public OrpakWSExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrpakWSExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public OrpakWSExceptions(String message) {
		super(message);
	}

	public OrpakWSExceptions() {
		super();
	}

	public OrpakWSExceptions(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
