package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.DepartmentMovementDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListMovimientosDeptoCtrlResultDTO extends GenericResultDTO{
	
	private Optional<List<DepartmentMovementDTO>> listadoMovimientosDepto;
	
	private Optional<Long> count;

}
