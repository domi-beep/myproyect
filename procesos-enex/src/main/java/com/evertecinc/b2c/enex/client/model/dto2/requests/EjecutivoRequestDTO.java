package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EjecutivoRequestDTO extends CriterioBusquedaGenericoDTO{

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("rut")
	private String rut;
	
	@JsonProperty("idActual")
	private Long idActual;
}
