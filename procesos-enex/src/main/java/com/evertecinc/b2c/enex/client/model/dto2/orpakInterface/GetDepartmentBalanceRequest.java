package com.evertecinc.b2c.enex.client.model.dto2.orpakInterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDepartmentBalanceRequest {
	
	private String departmentid;
	private String balanceType;

}
