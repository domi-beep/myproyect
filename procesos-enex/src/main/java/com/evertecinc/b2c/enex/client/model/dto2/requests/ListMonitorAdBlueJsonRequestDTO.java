package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListMonitorAdBlueJsonRequestDTO extends CriterioBusquedaGenericoDTO{
	
	private String rut;
	private String razonSocial;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;
	private String status;

}
