package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteListadoClientesDTO {
	
	private String fechaCreacion;
	private String razonSocial;
	private String rut;
	private Long tarjetasActivas;
	private Long totalTarjetas;
	private String tipoCliente;
	private Double porcentajeTarjetasActivas;
	
	public ReporteListadoClientesDTO(String fechaCreacion, String razonSocial, String rut, 
			Long tarjetasActivas, Long totalTarjetas, String tipoCliente) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.razonSocial = razonSocial;
		this.rut = rut;
		this.tarjetasActivas = tarjetasActivas;
		this.totalTarjetas = totalTarjetas;
		this.tipoCliente = tipoCliente;
		this.porcentajeTarjetasActivas = (totalTarjetas == 0) ? 0.0 : (tarjetasActivas.doubleValue() / totalTarjetas.doubleValue()) * 100;
	}
}
