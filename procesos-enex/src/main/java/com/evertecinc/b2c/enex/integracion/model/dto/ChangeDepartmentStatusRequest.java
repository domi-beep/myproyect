package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDepartmentStatusRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2063641649664438902L;
	
	private String customercode;
	private String fleetdepartment;
	private String status;

}
