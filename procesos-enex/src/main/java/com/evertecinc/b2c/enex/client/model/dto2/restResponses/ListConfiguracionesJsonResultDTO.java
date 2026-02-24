package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ConfiguracionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListConfiguracionesJsonResultDTO extends GenericResultDTO {

	@JsonProperty("listaConfiguraciones")
	private Optional<List<ConfiguracionDTO>> listaConfiguraciones;

	@JsonProperty("countListaConfiguraciones")
	private Optional<Long> countListaConfiguraciones;

}
