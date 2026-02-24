package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteComprasAnualesDTO {

	private String mes;
	private BigDecimal montoAnterior;
	private BigDecimal montoActual;
	private BigDecimal monto;
	
    public ReporteComprasAnualesDTO() {
    	
    }
    

	public ReporteComprasAnualesDTO(String mes, BigDecimal montoAnterior,
			BigDecimal montoActual, BigDecimal monto) {
		this.mes = mes;
		this.montoAnterior = montoAnterior;
		this.montoActual = montoActual;
		this.monto = monto;
	}




	public ReporteComprasAnualesDTO(String mes, BigDecimal monto) {
		this.mes = mes;
		this.monto = monto;
	}
}
