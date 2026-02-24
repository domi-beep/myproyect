package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.DepartmentProductFinderDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListProductsByClientJsonCtrlResultDTO extends GenericResultDTO{
	
	private Optional<List<DepartmentProductFinderDTO>> listado;
	

}
