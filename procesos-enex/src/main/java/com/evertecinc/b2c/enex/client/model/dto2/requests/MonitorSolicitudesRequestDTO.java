package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorSolicitudesRequestDTO extends CriterioBusquedaGenericoDTO{
	
	private String rut;
	private String razonSocial;
	private String tipoCliente;
	private String tipoSaf;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFinal;
	private Integer cantidad;
	private String status;
	private Long idElement;
	private Boolean listaFO;

}
                                                                                 