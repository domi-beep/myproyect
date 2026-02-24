package com.evertecinc.b2c.enex.client.model.dto2.requests;

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
public class AreaRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "active", access = JsonProperty.Access.WRITE_ONLY)
	private String active;
	private Long idRegion;
	
}
