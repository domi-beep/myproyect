package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationClientsCriterioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2456766778728482335L;
	private Integer idClient;
	private Integer idDepartment;
	private String tipoCliente;
	private String stationCode;

}
