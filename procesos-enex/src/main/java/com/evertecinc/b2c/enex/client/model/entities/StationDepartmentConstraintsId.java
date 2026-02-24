package com.evertecinc.b2c.enex.client.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDepartmentConstraintsId {

	private String stationCode;
    private Long idDepartment;
	
}
