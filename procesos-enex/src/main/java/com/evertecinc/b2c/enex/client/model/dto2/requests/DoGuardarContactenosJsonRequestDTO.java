package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoGuardarContactenosJsonRequestDTO extends CriterioBusquedaGenericoSessionDTO {

	@JsonProperty("motivo")
	private Long motivo;

	@JsonProperty("comentario")
	private String comentario;

}
