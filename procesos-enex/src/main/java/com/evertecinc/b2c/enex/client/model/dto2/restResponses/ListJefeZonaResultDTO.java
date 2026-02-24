package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.JefeZonaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListJefeZonaResultDTO extends GenericResultDTO{
	
	@JsonProperty("listaJefeZona")
	private Optional<List<JefeZonaDTO>> listaJefeZona;
	
	@JsonProperty("countListaJefeZona")
	private Optional<Long> countListaJefeZona;
	

}
