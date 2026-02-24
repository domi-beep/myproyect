package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.evertecinc.b2c.enex.client.model.dto2.StationConstraintDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewFichaTarjetaCtrlResultDTO extends GenericResultDTO{

	private Optional<MonitorCardDTO> tarjetaMonitor;
	
	private Optional<VehicleDTO> vehiculo;

	private Optional<CardDTO> card;
	
	private Optional<List<StationConstraintDTO>> constraints_personalizados;

	private Optional<String> loadMail1;
	private Optional<String> loadMail2;
	
	private Optional<String> stockMail1;
	private Optional<String> stockMail2;
	
	private Optional<ClientDTO> cliente;

	private Optional<DepartmentDTO> departamento;

	private Optional<Map<String, Object>> model;
	
}
