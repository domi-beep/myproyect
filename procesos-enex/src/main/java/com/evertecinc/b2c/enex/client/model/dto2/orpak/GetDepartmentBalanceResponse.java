package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDepartmentBalanceResponse {
	
	private double balance;
	private String balancetype;
	private String code;
	private String StockType;
	private String description;
	private Integer returnCode;
	private String message;
	private String uri;
	

}
