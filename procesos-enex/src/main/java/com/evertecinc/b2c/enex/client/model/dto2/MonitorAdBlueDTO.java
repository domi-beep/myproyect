package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MonitorAdBlueDTO {
	
	private Long idQueue; 
	private Long idCliente;
	private String rut;
	private String razonSocial;
	private String tipoCliente;
	private LocalDate fechaIngreso;
	private LocalDate fechaFinal;
	private String tipoSaf;
	private String usuarioAut;
	private String usuarioSolicita;
	private String direccion;
	private LocalDate dateOut;
	private Integer cantidad;
	private String data;
	private String status;
	private String motivo;
	
	
	public MonitorAdBlueDTO(Long idQueue, String rut, Long idCliente, String razonSocial, String tipoSaf, String data, String status, LocalDate fechaIngreso,
			LocalDate fechaFinal) {
		super();
		this.idQueue = idQueue;
		this.idCliente = idCliente;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.fechaIngreso = fechaIngreso;
		this.fechaFinal = fechaFinal;
		this.tipoSaf = tipoSaf;
		this.data = data;
		this.status = status;
	}
	
	

}
