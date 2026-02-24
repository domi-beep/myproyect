package com.evertecinc.b2c.enex.client.model.dto2.criterio;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterioMonitorTipoSafDTO extends CriterioBusquedaGenericoDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 540786591902231470L;
	
	private String tipo;
	private Long idQueue;
	private Long idElement;
	private String data;
	private Long task;
	private boolean taskisnotnull;
	private boolean limitHistory;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private List<String> tipos;

}
