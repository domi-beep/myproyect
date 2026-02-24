package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CreateDepartmentRequest {
	
	private String operationtype;
	private String customercode;
	private String departmentdescription;
	private String newdepartmentdescription;

}
