package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteCantidadClientesDTO {
	
	private String mes;
	
	private Long cantidadAnterior;
	
	private Long cantidadActual;
	
	
	public ReporteCantidadClientesDTO(String mes, 
			Long cantidadAnterior, 
			Long cantidadActual) {
		super();
		this.mes = mes;
		this.cantidadAnterior = cantidadAnterior;
		this.cantidadActual = cantidadActual;
	}
}
