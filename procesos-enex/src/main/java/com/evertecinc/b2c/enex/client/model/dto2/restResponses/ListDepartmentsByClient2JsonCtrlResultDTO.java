package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListDepartmentsByClient2JsonCtrlResultDTO extends GenericResultDTO{
	
	private Optional<List<DepartmentDTO>> listaDepartamentos;
	
	private Optional<Long> countListaDepartamentos;

}
