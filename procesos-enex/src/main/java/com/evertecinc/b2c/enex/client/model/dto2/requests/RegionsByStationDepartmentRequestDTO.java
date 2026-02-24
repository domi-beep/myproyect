package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegionsByStationDepartmentRequestDTO {
	
	private Long idDepartment;
	private Long idClient;
	private String clientType;
	private Boolean controlPass;
	
}
