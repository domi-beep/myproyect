package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoSetConstraintsByDepartmentRequestDTO extends CriterioBusquedaGenericoDTO{
	@JsonProperty(value = "mode")
	private String mode;

	@JsonProperty(value = "idDepartment")
	private Long idDepartment;

	@JsonProperty(value = "stationCode")
	private String stationCode;
}
