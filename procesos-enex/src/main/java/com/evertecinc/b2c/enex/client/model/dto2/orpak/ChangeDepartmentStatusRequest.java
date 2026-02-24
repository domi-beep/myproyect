package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ChangeDepartmentStatusRequest {
	
	private String customercode;
	private String fleetdepartment;
	private String status;
	

}
