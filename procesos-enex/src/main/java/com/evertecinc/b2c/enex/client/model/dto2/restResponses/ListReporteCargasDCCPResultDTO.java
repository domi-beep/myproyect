package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListReporteCargasDCCPResultDTO extends GenericResultDTO{
	@JsonProperty("listadoCargasDCCP")
	private Optional<List<ClientDTO>> listadoCargasDCCP;
}
