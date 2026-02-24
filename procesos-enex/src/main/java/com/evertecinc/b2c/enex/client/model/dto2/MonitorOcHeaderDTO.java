package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorOcHeaderDTO {
	
	private String numeroOc;
	private String rutCliente;
	private String nombre;
	private String estado;
	private String pdf;
	private Integer cantidad;
	private Long idClient;
	private Long idOc;
	private LocalDateTime fechaIngreso;
	private BigDecimal monto;
	private String tipoDoc;
	private String numFactura; 
	private String nombreUsuario;

}
