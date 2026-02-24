package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto.ZoneDTO;
import com.evertecinc.b2c.enex.client.model.dto2.StationConstraintDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListConstraintsByCardJsonResultDTO extends GenericResultDTO {

	@JsonProperty("ListadoConstraints")
	private Optional<List<StationConstraintDTO>> ListadoConstraints;

	@JsonProperty("listadoZonas")
	private Optional<List<ZoneDTO>> listadoZonas;

	@JsonProperty("listadoRegiones")
	private Optional<List<RegionDTO>> listadoRegiones;

	@JsonProperty("listadoAreas")
	private Optional<List<AreaDTO>> listadoAreas;

	@JsonProperty("listadoStations")
	private Optional<List<StationsDTO>> listadoStations;

}
