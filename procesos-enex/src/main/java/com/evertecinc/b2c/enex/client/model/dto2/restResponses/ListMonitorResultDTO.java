package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListMonitorResultDTO extends GenericResultDTO {
	
	@JsonProperty("listMonitor")
	private Optional<List<MonitorDTO>> listMonitor;

	@JsonProperty("countMonitores")
	private Optional<Long> countListCards;
	

}
