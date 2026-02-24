package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorPagoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorPagoResultDTO extends GenericResultDTO{
	
	@JsonProperty("listadoMonitorPagos")
	private Optional<List<MonitorPagoDTO>> listMonitorPagos;
	
	@JsonProperty("countListadoMonitorPagos")
	private Optional<Long> countlistMonitorPagos;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitorPagoResultDTO [listMonitorPagos=");
		builder.append(listMonitorPagos);
		builder.append(", countlistMonitorCompras=");
		builder.append(countlistMonitorPagos);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
