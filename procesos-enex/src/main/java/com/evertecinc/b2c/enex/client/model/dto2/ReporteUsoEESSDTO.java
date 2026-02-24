package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteUsoEESSDTO extends CriterioBusquedaGenericoDTO{
	private String region;
	private String direccion;
	private String codigo;
	private BigDecimal monto;
	private Integer cantidad;
	private Double litros;

	public ReporteUsoEESSDTO() {
	}

	public ReporteUsoEESSDTO(String region, String direccion, String codigo,
			BigDecimal monto, Integer cantidad, Double litros) {
		this.region = region;
		this.direccion = direccion;
		this.codigo = codigo;
		this.monto = monto;
		this.cantidad = cantidad;
		this.litros = litros;
	}
}
