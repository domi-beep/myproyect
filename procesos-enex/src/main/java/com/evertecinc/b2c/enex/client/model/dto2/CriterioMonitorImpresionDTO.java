package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CriterioMonitorImpresionDTO {
	
	private String rut;
	private String razonSocial;
	private String patente;
	private String tipoCliente;
	private String tipoSaf;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;
	private int id_vehicle;
	private String estado;
	private Long task;
	private int cantidad;
	private String origen;
	private Long idTarea;
	private String estadoVehReqs;

}
