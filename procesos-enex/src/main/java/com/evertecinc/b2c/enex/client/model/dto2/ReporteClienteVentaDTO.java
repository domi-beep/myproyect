package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteClienteVentaDTO {
	private Long idClient;
	private String upi;
	private String legalName;
	private LocalDateTime fechaCreacionCliente;
	private LocalDateTime fechaUltimaCompra;
	private BigDecimal montoTotalAcumulado;
	private BigDecimal montoUltimaCompra;
	private Integer cantidad;
	private String tipoCliente;
	private Double tarjetasActivas;
	private Double totalTarjetas;
	
	
	public ReporteClienteVentaDTO(Long idClient, String upi, String legalName, LocalDateTime fechaCreacionCliente,
			LocalDateTime fechaUltimaCompra, BigDecimal montoTotalAcumulado, BigDecimal montoUltimaCompra,
			String tipoCliente, Double tarjetasActivas, Double totalTarjetas) {
		super();
		this.idClient = idClient;
		this.upi = upi;
		this.legalName = legalName;
		this.fechaCreacionCliente = fechaCreacionCliente;
		this.fechaUltimaCompra = fechaUltimaCompra;
		this.montoTotalAcumulado = montoTotalAcumulado;
		this.montoUltimaCompra = montoUltimaCompra;
		this.tipoCliente = tipoCliente;
		this.tarjetasActivas = tarjetasActivas;
		this.totalTarjetas = totalTarjetas;
	}
}
