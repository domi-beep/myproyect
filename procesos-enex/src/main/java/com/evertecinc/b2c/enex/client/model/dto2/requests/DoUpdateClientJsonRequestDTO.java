package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoUpdateClientJsonRequestDTO  extends CriterioBusquedaGenericoDTO{
	
	private Integer idCliente;
	private Integer idEjecutivo;

}
