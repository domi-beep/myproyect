package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListDocumentsJsonRequestDTO extends CriterioBusquedaGenericoSessionDTO {

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("monto")
	private Long monto;

	@JsonProperty("dateFin")
	private String dateFin;

	@JsonProperty("dateInicio")
	private String dateInicio;

	@JsonProperty("numDoc")
	private String numDoc;

	@JsonProperty("tipoDoc")
	private String tipoDoc;

	@JsonProperty("todos")
	private boolean todos = false;

	@JsonProperty("vencer")
	private boolean vencer = false;

	@JsonProperty("vencidos")
	private boolean vencidos = false;

}
