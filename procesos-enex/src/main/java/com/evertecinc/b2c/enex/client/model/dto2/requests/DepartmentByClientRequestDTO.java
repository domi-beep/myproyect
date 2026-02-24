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
public class DepartmentByClientRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("name")
	private String name;

	@JsonProperty("plate")
	private String plate;

	@JsonProperty("codigo")
	private String codigo;

	@JsonProperty("idCliente")
	@Schema(hidden = true)
	private Long idCliente;

	@JsonProperty("idUser")
	@Schema(hidden = true)
	private Long idUser;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("restrAmountMax")
	private Double restrAmountMax;
	
	// ******************* PARA FINES DE SESSION *******************
	@JsonProperty("upiSession")
	private String upiSession;

	@JsonProperty("rutUsuarioSession")
	private String rutUsuarioSession;

	@JsonProperty("portal")
	private String portal;

	@JsonProperty("clientType")
	private String clientType;
	// ******************* PARA FINES DE SESSION FIN *******************

}
