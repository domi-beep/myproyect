package com.evertecinc.b2c.enex.integracion.exception;

import org.slf4j.Logger;

@SuppressWarnings("serial")
public class OrpakException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Error al realizar operación";

    public OrpakException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrpakException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrpakException(String message) {
        super(message);
    }

    public OrpakException() {
        super();
    }

    public OrpakException(Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }

    public OrpakException(Exception e, Logger logger) {
        super(ERROR_MESSAGE, e);
        logger.error("<---ERROR---> ", e);
        logger.error("<---ERROR MESSAGE---> " + e.toString());
        StackTraceElement[] stack = e.getStackTrace();
        for (StackTraceElement element : stack) {
            String filename = element.getFileName();
            if (filename == null) {
                continue;
            }
            String className = element.getClassName();
            String methodName = element.getMethodName();
            int line = element.getLineNumber();
            logger.error(filename + " " + className + " " + methodName + " " + line);
        }
    }

	public OrpakException(String message, Exception e, Logger logger) {
		super(ERROR_MESSAGE, e);
		logger.error("<---STRING---> ", message);
        logger.error("<---ERROR---> ", e);
        logger.error("<---ERROR MESSAGE---> " + e.toString());
        StackTraceElement[] stack = e.getStackTrace();
        for (StackTraceElement element : stack) {
            String filename = element.getFileName();
            if (filename == null) {
                continue;
            }
            String className = element.getClassName();
            String methodName = element.getMethodName();
            int line = element.getLineNumber();
            logger.error(filename + " " + className + " " + methodName + " " + line);
        }
	}

	public OrpakException(String message, Logger logger) {
		super(message);
		logger.error("<---STRING---> ", message);
	}
}
