package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Map;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewEditDepartamentoCtrlResultDTO extends GenericResultDTO{
	
	@JsonProperty("model")
	private Map<String, Object> model;
	
	@JsonProperty("cliente")
	private ClientDTO client;
	
	@JsonProperty("departamento")
	private DepartmentDTO departamento;

}
