package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorVehiclesDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlateClientsResultDTO extends GenericResultDTO {
	
	@JsonProperty("listadoPatentesClientes")
	private Optional<List<MonitorVehiclesDTO>> listPlateClients;
	
	@JsonProperty("countListadoPatentesClientes")
	private Optional<Long> countPlateClients;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PatentesClientesResultDTO [listPatentesClientes=");
		builder.append(listPlateClients);
		builder.append(", countListadoPatentesClientes=");
		builder.append(countPlateClients);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}



	
	

}
