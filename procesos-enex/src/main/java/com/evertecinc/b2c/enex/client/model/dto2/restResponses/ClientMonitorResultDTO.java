package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientMonitorResultDTO extends GenericResultDTO {
	
	@JsonProperty("listadoClientes")
	private Optional<List<ClientMonitorDTO>> listClientes;
	
	@JsonProperty("countListadoClientes")
	private Optional<Long> countListadoClientes;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientMonitorResultDTO [listClientes=");
		builder.append(listClientes);
		builder.append(", countListadoClientes=");
		builder.append(countListadoClientes);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	
	

}
