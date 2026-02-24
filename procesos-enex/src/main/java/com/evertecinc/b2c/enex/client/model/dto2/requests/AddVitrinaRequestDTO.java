package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddVitrinaRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	@JsonProperty("codigo")
	private String codigo;
	
	@JsonProperty("estado")
	private String estado;
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("portal")
	private String portal;
	
	@JsonProperty("posicion")
	private String posicion;
	
}
