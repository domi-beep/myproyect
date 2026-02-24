package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SafReportDTO {
	
	private String tipo;
	private Integer cantidad;
	private Integer intentos;
	private LocalDateTime fechaIngreso;
	
	public SafReportDTO(String tipo, Long cantidad, Long intentos, LocalDateTime fechaIngreso) {
        this.tipo = tipo;
        this.cantidad = cantidad != null ? cantidad.intValue() : null;
        this.intentos = intentos != null ? intentos.intValue() : null;
        this.fechaIngreso = fechaIngreso;
    }

}
