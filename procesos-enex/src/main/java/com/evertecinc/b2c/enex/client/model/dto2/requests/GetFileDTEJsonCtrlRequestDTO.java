package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetFileDTEJsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("folio")
	private String folio;

	@JsonProperty("tipoDocumento")
	private String tipoDocumento;

	@JsonProperty("tipoCliente")
	private String tipoCliente;

	@JsonProperty("src")
	private String src;

	@JsonProperty("fecha")
	private String fecha;

}
