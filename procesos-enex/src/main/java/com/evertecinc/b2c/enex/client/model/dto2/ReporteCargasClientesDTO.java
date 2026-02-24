package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteCargasClientesDTO {
	private Long idClient;
	private String upi;
	private String legalName;
	private String clientType;
	private BigDecimal litrosAcumulados;
	private BigDecimal montoTotalAcumulado;
	private Double tarjetasActivas;
	private Double totalTarjetas;
	private LocalDateTime fechaUltimaCarga;
	
	public ReporteCargasClientesDTO(Long idClient, String upi, String legalName, String clientType, BigDecimal litrosAcumulados,
	         BigDecimal montoTotalAcumulado, Double tarjetasActivas, Double totalTarjetas,  LocalDateTime fechaUltimaCarga) {
	    this.idClient = idClient;
	    this.upi = upi;
	    this.legalName = legalName;
	    this.clientType = clientType;
	    this.litrosAcumulados = litrosAcumulados;
	    this.montoTotalAcumulado = montoTotalAcumulado;
	    this.tarjetasActivas = tarjetasActivas;
	    this.totalTarjetas = totalTarjetas;
	    this.fechaUltimaCarga = fechaUltimaCarga;
	}

}
