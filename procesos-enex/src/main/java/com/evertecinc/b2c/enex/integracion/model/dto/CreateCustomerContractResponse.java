package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerContractResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5036022287746737234L;

	private Integer returnCode;
	private String message;
	private String uri;
	
	
    
}
