package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeleteDeptoClientJsonRequestDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("idDepto")
	private Long idDepto;
	
	// ******************* PARA FINES DE SESSION *******************
	@JsonProperty("upiSession")
	private String upiSession;

	@JsonProperty("rutUsuarioSession")
	private String rutUsuarioSession;

	@JsonProperty("portal")
	private String portal;

	@JsonProperty("clientType")
	private String clientType;
	// ******************* PARA FINES DE SESSION FIN *******************

}
