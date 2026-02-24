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
public class DepartmentByUserRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("name")
	private String name;

	@JsonProperty("plate")
	private String plate;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("codigo")
	private String codigo;

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("rutUsuario")
	private String rutUsuario;

	@JsonProperty("idCliente")
	@Schema(hidden = true)
	private Long idCliente;
	
}
