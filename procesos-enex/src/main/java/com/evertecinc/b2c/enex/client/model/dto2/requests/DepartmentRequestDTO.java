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
public class DepartmentRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("idClient")
	@Schema(hidden = true)
	private Long idClient;

	@JsonProperty("idUser")
	@Schema(hidden = true)
	private Long idUser;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("rolUser")
	private Integer rolUser;

}
