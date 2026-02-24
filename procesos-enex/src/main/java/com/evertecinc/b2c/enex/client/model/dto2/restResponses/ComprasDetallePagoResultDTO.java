package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComprasDetallePagoResultDTO extends GenericResultDTO{
	
	@JsonProperty("ListaComprasDetallePago")
	private Optional<List<MonitorComprasDTO>> listaComprasDetallePago;
	
	@JsonProperty("countListaComprasDetallePago")
	private Optional<Long> countListaComprasDetallePago;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComprasDetallePagoResultDTO [listaComprasDetallePago=");
		builder.append(listaComprasDetallePago);
		builder.append(", countListaComprasDetallePago=");
		builder.append(countListaComprasDetallePago);
		builder.append("]");
		return builder.toString();
	}

	
}
