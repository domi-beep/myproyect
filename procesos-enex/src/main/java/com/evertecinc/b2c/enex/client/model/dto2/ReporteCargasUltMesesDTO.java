package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteCargasUltMesesDTO extends CriterioBusquedaGenericoDTO{
	private String mes;
	private BigDecimal monto;
	private BigDecimal litros;
	
	public ReporteCargasUltMesesDTO() {
	}

	public ReporteCargasUltMesesDTO(String mes, BigDecimal monto,
			BigDecimal litros) {
		this.mes = mes;
		this.monto = monto;
		this.litros = litros;
	}
}
