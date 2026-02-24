package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteListadoUsoEESSDTO extends CriterioBusquedaGenericoDTO{

    private Integer mes; 
    private Integer anio; 
    private String region; 
    private String codigo; 
    private String comuna;
    private String estacion;
    private BigDecimal monto; 
    private Long cantidad;
    private BigDecimal litros;
    private String producto;

	public ReporteListadoUsoEESSDTO() {
	}

	public ReporteListadoUsoEESSDTO(Integer mes, Integer anio, String region, String codigo, String comuna,
			String estacion, BigDecimal monto, Long cantidad, BigDecimal litros, String producto) {
		super();
		this.mes = mes;
		this.anio = anio;
		this.region = region;
		this.codigo = codigo;
		this.comuna = comuna;
		this.estacion = estacion;
		this.monto = monto;
		this.cantidad = cantidad;
		this.litros = litros;
		this.producto = producto;
	}
	
	public Long getPrecioPromedio() {
        if (litros != null && litros.longValue() != 0) {
            return monto.longValue() / litros.longValue();
        }
        return 0L;
    }
}
