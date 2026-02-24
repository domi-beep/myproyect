package com.evertecinc.b2c.enex.client.model.dto2.criterio;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriterioBusquedaGenericoSessionDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("upiSession")
	private String upiSession;

	@JsonProperty("rutUsuarioSession")
	private String rutUsuarioSession;

	@JsonProperty("portalSession")
	private String portalSession;

	@JsonProperty("clientType")
	private String clientType;

}
