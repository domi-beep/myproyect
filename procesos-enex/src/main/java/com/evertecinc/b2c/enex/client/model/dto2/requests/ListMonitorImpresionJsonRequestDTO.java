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
public class ListMonitorImpresionJsonRequestDTO extends CriterioBusquedaGenericoDTO{
	
	private String rut;
	private String patente;
	private String tipoCliente;
	private String tipoSaf;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;
	private String estado;

}
