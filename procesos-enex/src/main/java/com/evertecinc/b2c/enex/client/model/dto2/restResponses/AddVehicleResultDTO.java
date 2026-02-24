package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddVehicleResultDTO extends GenericResultDTO {
	
	@JsonProperty("countVehiculosProcesados")
	private Optional<Long> countVehiculosProcesados;
	
	@JsonProperty("vehiculosExistentes")
	private Optional<List<String>> vehiculosExistentes;

	@JsonProperty("vehiculosExito")
	private Optional<List<String>> vehiculosExito;

	@JsonProperty("vehiculosError")
	private Optional<List<String>> vehiculosError;

	@JsonProperty("vehiculosErrorFormatoPatente")
	private Optional<List<String>> vehiculosErrorFormatoPatente;
}
