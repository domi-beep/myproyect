package com.evertecinc.b2c.enex.process.jde.service;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.process.exceptions.ParameterNotFoundException;
import com.evertecinc.b2c.enex.process.jde.exceptions.ProcessJDEException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;

public interface IProcessJdeService {

	public Optional<List<ClientDTO>> getClientJdeByFactDepartmentStatus(boolean factDepartmentStatus);

	public List<DepartmentDTO> getListDepartmentByClient(DepartmentCriterioDTO criteriaDeptos);

	public Integer getCorrelativo() throws ParameterNotFoundException;

	public void popOrder(MessageDTO messageDTO, String nombreArchivo, boolean agregar) throws ProcessJDEException;

	public void popOC(MessageDTO messageDTO, String nombreArchivo, boolean agregar) throws ProcessJDEException;

	public void sendAlertaProblemaStockDepto();

	public void sendAlertaProblemaStockTarjeta();

	public void sendAlertaProblemaStockCliente();
	
	

}
