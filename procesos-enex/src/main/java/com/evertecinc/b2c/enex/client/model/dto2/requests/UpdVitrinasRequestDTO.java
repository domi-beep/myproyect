package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdVitrinasRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	@JsonProperty("idVitrinas")
 	private Long idVitrinas;
	
	@JsonProperty("codigo")
	private String codigo;
	
	@JsonProperty("estado")
	private String estado;
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("portal")
	private String portal;    
	
}
