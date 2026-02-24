package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListDepartmentsByClient2JsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("plate")
	private String plate;
	
	@JsonProperty("tipoCliente")
	private String tipoCliente;
	
	@JsonProperty("idClient")
	private Long idClient;
	
}
