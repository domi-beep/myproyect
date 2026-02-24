package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorComprasResultDTO extends GenericResultDTO{
	
	@JsonProperty("listadoMonitorCompras")
	private Optional<List<MonitorComprasDTO>> listMonitorCompras;
	
	@JsonProperty("countListadoMonitorCompras")
	private Optional<Long> countlistMonitorCompras;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitorComprasResultDTO [listMonitorCompras=");
		builder.append(listMonitorCompras);
		builder.append(", countlistMonitorCompras=");
		builder.append(countlistMonitorCompras);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	
	


}
