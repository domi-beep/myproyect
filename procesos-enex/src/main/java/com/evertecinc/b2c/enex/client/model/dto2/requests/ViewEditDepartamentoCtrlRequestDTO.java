package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewEditDepartamentoCtrlRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("tabTarjeta")
	private String tabTarjeta;
	
	@JsonProperty("idClient")
	private Long idClient;
	
	

}
