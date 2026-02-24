package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationClientsRequestDTO extends CriterioBusquedaGenericoDTO{

	private Long idClient;
	private Long idDepartment;
	private String clientType;
	private String stationCode;
	
	/**
	 * @param idClient
	 * @param idDepartment
	 * @param clientType
	 */
	public StationClientsRequestDTO(Long idClient, Long idDepartment, String clientType) {
		super();
		this.idClient = idClient;
		this.idDepartment = idDepartment;
		this.clientType = clientType;
	}
	
	
}
