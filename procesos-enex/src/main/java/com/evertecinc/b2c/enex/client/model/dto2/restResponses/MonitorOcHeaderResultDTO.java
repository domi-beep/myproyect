package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorOcHeaderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorOcHeaderResultDTO extends GenericResultDTO{
	
	@JsonProperty("listaMonitorOcHeaderDTO")
	private Optional<List<MonitorOcHeaderDTO>> listaMonitorOcHeaderDTO;
	
	@JsonProperty("countListaMonitorOcHeaderDTO")
	private Optional<Long> countListaMonitorOcHeaderDTO;

}
