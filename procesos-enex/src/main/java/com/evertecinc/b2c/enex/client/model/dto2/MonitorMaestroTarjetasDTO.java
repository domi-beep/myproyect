package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorMaestroTarjetasDTO {
	
	private String idMifare;
	private String estado;
	private Long idVehicle;
	private String patente;
	private String cardNumber;
	private LocalDateTime fechaUso;
	private Integer cantidad;
	
	public MonitorMaestroTarjetasDTO(String idMifare, String estado, Long idVehicle, String patente, String cardNumber,
			LocalDateTime fechaUso, Integer cantidad) {
		super();
		this.idMifare = idMifare;
		this.estado = estado;
		this.idVehicle = idVehicle;
		this.patente = patente;
		this.cardNumber = cardNumber;
		this.fechaUso = fechaUso;
		this.cantidad = cantidad;
	}
	
	

}
