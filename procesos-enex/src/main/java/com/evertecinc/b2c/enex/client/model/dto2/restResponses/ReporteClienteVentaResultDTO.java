package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ReporteClienteVentaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteClienteVentaResultDTO extends GenericResultDTO{

	@JsonProperty("listReporteClienteVenta")
	private Optional<List<ReporteClienteVentaDTO>> listReporteClienteVenta;
	
	@JsonProperty("countListReporteClienteVenta")
	private Optional<Long> countListReporteClienteVenta;
}
