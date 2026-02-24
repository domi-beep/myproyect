package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddDepositoJsonCtrlRequestDTO extends CriterioBusquedaGenericoSessionDTO {

	@JsonProperty("listadoItems")
	private String listadoItems;
	
	@JsonProperty("deposito")
	private Long deposito;
	
	@JsonProperty("comprobante")
	private String comprobante;
	
	@JsonProperty("banco")
	private String banco;
	
	@JsonProperty("idClient")
	private Long idClient;

	
}
