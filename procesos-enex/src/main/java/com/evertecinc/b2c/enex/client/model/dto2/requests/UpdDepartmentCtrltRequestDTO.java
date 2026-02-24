package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdDepartmentCtrltRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idDepartamento")
	private Long idDepartamento;
	
	@JsonProperty("manejoSaldo")
	private String manejoSaldo;
	
	@JsonProperty("nameDepartment")
	private String nameDepartment;
	
	@JsonProperty("statusActual")
	private String statusActual;
	
	@JsonProperty("rutUsuarioBO")
	private String rutUsuarioBO;
	
}
