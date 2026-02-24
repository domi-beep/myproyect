package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AreaByStationsDepartmentRequestDTO {

	private List<Long> regiones;
	private Long idDepartment;
	private String clientType;
	private Boolean controlPass;
	private Long idClient;
	
}
