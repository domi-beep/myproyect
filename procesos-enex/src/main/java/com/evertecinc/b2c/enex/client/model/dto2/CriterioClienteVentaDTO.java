package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CriterioClienteVentaDTO {
	
	private String upi;
	private String legalName;
	private String estado;
	private String clientType;
	private String repTipo;
	private Long clientId;

}
