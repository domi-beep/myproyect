package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorColaSafDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListMonitorTipoSafResultDTO extends GenericResultDTO {
	
	@JsonProperty("listMonitor")
	private Optional<List<MonitorColaSafDTO>> listMonitor;

	@JsonProperty("countMonitores")
	private Optional<Long> countTotal;
	

}
