package com.evertecinc.b2c.enex.client.service;

import com.evertecinc.b2c.enex.client.exceptions.VariableException;

public interface IVariableService {
	
	public String getVariableAsString(String codigoPortal,String nombreVariable) throws VariableException;
	
	public Long getVariableAsLong(String codigoPortal,String nombreVariable) throws VariableException;
	
	public Integer getVariableAsInteger(String codigoPortal,String nombreVariable) throws VariableException;

}
