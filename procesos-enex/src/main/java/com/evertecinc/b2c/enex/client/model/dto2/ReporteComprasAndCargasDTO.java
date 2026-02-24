package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteComprasAndCargasDTO extends CriterioBusquedaGenericoDTO{
	private String mes;
	private BigDecimal compras;
	private BigDecimal cargas;

	public ReporteComprasAndCargasDTO() {
	}

	public ReporteComprasAndCargasDTO(String mes, BigDecimal compras,
			BigDecimal cargas) {
		this.mes = mes;
		this.compras = compras;
		this.cargas = cargas;
	}
}
