package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ReporteCargasClientesDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteCargasClientesResultDTO extends GenericResultDTO{
	
	@JsonProperty("listReporteCargasClientes")
	private Optional<List<ReporteCargasClientesDTO>> listReporteCargasClientes;
	
	@JsonProperty("countListReporteCargasClientes")
	private Optional<Long> countListReporteCargasClientes;
}
