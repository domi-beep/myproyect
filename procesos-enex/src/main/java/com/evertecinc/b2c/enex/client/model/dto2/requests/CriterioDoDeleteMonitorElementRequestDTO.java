package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterioDoDeleteMonitorElementRequestDTO extends CriterioBusquedaGenericoDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 540786591902231470L;
	
	private Long idQueue;

}
