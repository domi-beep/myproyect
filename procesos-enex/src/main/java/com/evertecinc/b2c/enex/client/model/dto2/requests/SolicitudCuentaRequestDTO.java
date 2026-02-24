package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCuentaRequestDTO {

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	
}
