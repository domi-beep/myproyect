package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.jde.HistorialPagoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListDocumentsJsonResultDTO extends GenericResultDTO {
	
	@JsonProperty("listHistorialPago")
	private Optional<List<HistorialPagoDTO>> listHistorialPago;

}
