package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StationAreaRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("nameStation")
	private String nameStation;

	@JsonProperty("nameArea")
	private String nameArea;

	@JsonProperty("nameRegion")
	private String nameRegion;

	@JsonProperty("nameCity")
	@Schema(hidden = true)
	private String nameCity;

	@JsonProperty("codeStation")
	private String codeStation;

	@JsonProperty("codeRegion")
	@Schema(hidden = true)
	private String codeRegion;

	@JsonProperty("codeArea")
	@Schema(hidden = true)
	private String codeArea;

	@JsonProperty("codeCity")
	@Schema(hidden = true)
	private String codeCity;

	@JsonProperty("cantidad")
	@Schema(hidden = true)
	private Integer cantidad;

	@JsonProperty("controlTotal")
	private Boolean controlTotal;

	@JsonProperty("latitud")
	@Schema(hidden = true)
	private String latitud;

	@JsonProperty("longitud")
	@Schema(hidden = true)
	private String longitud;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("idZone")
	private String codeElectrolinera;

}
