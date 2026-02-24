package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListMonitorSafJsonRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "tipoSaf")
	private String tipoSaf;
	
	@JsonProperty(value = "dateFirst")
	private LocalDateTime dateFirst;
	
	@JsonProperty(value = "dateEnd")
	private LocalDateTime dateEnd;
	
	@JsonProperty(value = "idQueue")
	private Long idQueue;
	
}
