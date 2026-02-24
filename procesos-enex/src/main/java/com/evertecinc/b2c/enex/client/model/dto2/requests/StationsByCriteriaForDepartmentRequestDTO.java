package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StationsByCriteriaForDepartmentRequestDTO {
	
	private List<Long> zonas;
	private List<Long> regiones;
	private List<Long> areas;
	private String clientType;
	private Long idDepartment;
	private Boolean controlPass;
	private Long idClient;

}
