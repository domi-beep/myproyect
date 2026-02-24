package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.jde.JdeInformeComprasDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListDetalleFacturaJsonResultDTO extends GenericResultDTO {
	
	@JsonProperty("listJdeInformeCompras")
	private Optional<List<JdeInformeComprasDTO>> listJdeInformeCompras;

}
