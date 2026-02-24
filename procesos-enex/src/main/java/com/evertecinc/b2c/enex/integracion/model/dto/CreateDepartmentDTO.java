package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7088024323018438281L;
	private String customerCode;
	private String departmentDescription;
	
	
}
