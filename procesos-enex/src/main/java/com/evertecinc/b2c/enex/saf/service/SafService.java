package com.evertecinc.b2c.enex.saf.service;

import java.math.BigDecimal;
import java.util.List;

import com.evertecinc.b2c.enex.saf.exceptions.SafException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;

public interface SafService {
	
	public void checkConexion() throws SafException;
	
	public List<SafQueue> getSaf(SafCriterioDTO dto) throws SafException;

	public void save(SafQueue safQueue) throws SafException;

	public void pushMessageHistory(MessageHistoryDTO message) throws SafException;

	public void updateMessage(MessageDTO message) throws SafException;

	public int getTotalSafConditionalsPendings(String safUpdateCardNumber, Long idQueue)  throws SafException;

	public void pushMessageUpdateDepartmentStatus(Long idElement)  throws SafException;

	public Long pushMessageSafConditional(MessageDTO message)  throws SafException;

	public void pushMessageUpdateDepartmentBalanceConditional(Long idElement, BigDecimal saldoDepto, String producto, String tipoDoc, Long idUtb2)  throws SafException;

	public void pushMessageUpdateCardNumberConditional(Long idVehicle, Long idUtb3)  throws SafException;

	public void pushMessageUpdateClient(Long idClient) throws SafException;

	public void pushMessageUpdateCardConstraint(Long idCard, String data) throws SafException;

	public List<MessageDTO> getSafDTO(SafCriterioDTO dto) throws SafException;

	public void updRetriesById(Long idQueue);

}
