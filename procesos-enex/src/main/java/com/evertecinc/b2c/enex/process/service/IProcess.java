package com.evertecinc.b2c.enex.process.service;

import com.evertecinc.b2c.enex.process.exceptions.ProcessException;

public interface IProcess {
	
	public void run() throws ProcessException;

}
