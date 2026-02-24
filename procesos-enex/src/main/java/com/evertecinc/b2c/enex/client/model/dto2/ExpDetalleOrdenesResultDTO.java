package com.evertecinc.b2c.enex.client.model.dto2;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.restResponses.GenericResultDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpDetalleOrdenesResultDTO extends GenericResultDTO {
	
	@JsonProperty("listaDetalleOrdenes")
	private Optional<List<MonitorComprasExpDetalleOrdenes>> listaExcelDetalleOrdenes;
	
	@JsonProperty("countListaDetalleOrdenes")
	private Optional<Long> countListaDetalleOrdenes;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpDetalleOrdenesResultDTO [listaExcelDetalleOrdenes=");
		builder.append(listaExcelDetalleOrdenes);
		builder.append(", countListaDetalleOrdenes=");
		builder.append(countListaDetalleOrdenes);
		builder.append("]");
		return builder.toString();
	}




	
}
