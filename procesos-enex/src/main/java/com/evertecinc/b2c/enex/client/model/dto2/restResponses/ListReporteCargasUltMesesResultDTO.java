package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ReporteCargasUltMesesDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListReporteCargasUltMesesResultDTO extends GenericResultDTO{
	
	@JsonProperty("listadoCargasUltMeses")
	private Optional<List<ReporteCargasUltMesesDTO>> listadoCargasUltMeses;


}