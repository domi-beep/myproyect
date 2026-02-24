package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetVitrinasRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idVitrina")
	private Long idVitrina;
	
	@JsonProperty("codigo")
	private String codigo;

	@JsonProperty("estado")
	private String estado;

	@JsonProperty("borrado")
	private Short borrado;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("portal")
	private String portal;

}
