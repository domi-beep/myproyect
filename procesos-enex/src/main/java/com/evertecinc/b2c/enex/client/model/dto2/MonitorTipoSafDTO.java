package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitorTipoSafDTO {
	
	private Long idQueue;
	private Long reintentos;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaUltimoIntento;
	private String ultimoError;
	
	
	public MonitorTipoSafDTO(Long idQueue, Long reintentos, LocalDateTime fechaIngreso,
			LocalDateTime fechaUltimoIntento, String ultimoError, String tipoSaf, Long task) {
		super();
		this.idQueue = idQueue;
		this.reintentos = reintentos;
		this.fechaIngreso = fechaIngreso;
		this.fechaUltimoIntento = fechaUltimoIntento;
		this.ultimoError = ultimoError;
		this.tipoSaf = tipoSaf;
		this.task = task;
	}
	private Long cantidad;
	private String tipoSaf;
	private Long task;
	private Long idElement;

}
