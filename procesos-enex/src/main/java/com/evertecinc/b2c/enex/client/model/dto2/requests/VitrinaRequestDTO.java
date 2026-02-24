package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class VitrinaRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("idVitrina")
	@Schema(hidden = true)
	private Long idVitrina;

	@JsonProperty("estado")
	@Schema(hidden = true)
	private String estado;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("codigo")
	@Schema(hidden = true)
	private String codigo;
	
}
