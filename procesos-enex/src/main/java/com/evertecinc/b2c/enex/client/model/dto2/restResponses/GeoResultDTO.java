package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto2.StationAreaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GeoResultDTO extends GenericResultDTO {
	
	@JsonProperty("listStationsPortal")
	private Optional<List<StationAreaDTO>> listStationsPortal;
	
	@JsonProperty("listaRegiones")
	private Optional<List<RegionDTO>> listaRegiones;
	
}
