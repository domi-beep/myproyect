package com.evertecinc.b2c.enex.client.model.dto2;
import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteDetalleComprasCargasDTO extends CriterioBusquedaGenericoDTO{
	
	private String mes;
	private String anio;
	private String razonSocial;
	private String rut;
	private String administrador;
	private BigDecimal compras;
	private BigDecimal cargas;
	private BigDecimal litros;
	private Double tarjetasActivas;
	private Double totalTarjetas;
	
	public ReporteDetalleComprasCargasDTO(String mes, String anio, String razonSocial, String rut, String administrador,
            BigDecimal compras, BigDecimal cargas, BigDecimal litros, Double tarjetasActivas, Double totalTarjetas) {
        super();
        this.mes = mes;
        this.anio = anio;
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.administrador = administrador;
        this.compras = compras;
        this.cargas = cargas;
        this.litros = litros;
        this.tarjetasActivas = tarjetasActivas;
        this.totalTarjetas = totalTarjetas;
    }
    
    public Double getPorcentajeTarjetasActivas() {
        if (totalTarjetas == 0) {
            return 0.0;
        } else {
            return (tarjetasActivas / (totalTarjetas / 100));
        }
    }
    
	public BigDecimal getSaldo() {
		return compras.subtract(cargas);
	}

}
