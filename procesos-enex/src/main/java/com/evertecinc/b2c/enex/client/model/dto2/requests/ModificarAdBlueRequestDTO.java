package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModificarAdBlueRequestDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("idUsuario")
	private Long idUsuario;
	
	@JsonProperty("productCode")
	private String productCode;

	@JsonProperty("idVehicle")
	private Long idVehicle;
	
	@JsonProperty("portal")
	private String portal;

}
