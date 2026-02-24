package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegionRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "idsZone", access = JsonProperty.Access.WRITE_ONLY)
	private List<Long> idsZone;
	
}
