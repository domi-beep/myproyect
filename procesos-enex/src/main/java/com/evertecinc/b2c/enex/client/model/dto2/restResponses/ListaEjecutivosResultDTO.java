package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.EjecutivoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListaEjecutivosResultDTO extends GenericResultDTO {
	
	@JsonProperty("listaEjecutivos")
	private Optional<List<EjecutivoDTO>> listaEjecutivos;

	@JsonProperty("countEjecutivos")
	private Optional<Long> countEjecutivos;

}
