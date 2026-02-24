package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetBannerByIdRequestDTO extends CriterioBusquedaGenericoDTO{

	private Long idBanner;
	private String codigo;        
	private String codigoPortal;  
	private String nombre;        
	private String estado;         
	
}
