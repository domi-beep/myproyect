package com.evertecinc.b2c.enex.integracion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDepartmentStatusResponse {
	
	private int returnCode;
	private String message;
	private String uri;

}
