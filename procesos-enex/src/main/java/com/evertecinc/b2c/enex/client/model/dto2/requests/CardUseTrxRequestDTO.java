package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardUseTrxRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idCliente")
	private String idCliente;
	
	@JsonProperty("rutCliente")
	private String rutCliente;
	
	@JsonProperty("fecha")
	private LocalDateTime fecha;
	
	@JsonProperty("patente")
	private String patente;

}
