package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListConstraintsByDepartmentJsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("zone")
	private String zone;
	
	@JsonProperty("region")
	private String region;
	
	@JsonProperty("area")
	private String area;
	
	@JsonProperty("nivel")
	private String nivel;
	
	@JsonProperty("controlpass")
	private Boolean controlpass;
	
	@JsonProperty("idClient")
	private Long idClient;
	
}
