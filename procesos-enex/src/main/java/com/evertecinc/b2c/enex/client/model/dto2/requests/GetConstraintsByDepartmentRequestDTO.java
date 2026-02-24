package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetConstraintsByDepartmentRequestDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("clientType")
	private String clientType;

}
