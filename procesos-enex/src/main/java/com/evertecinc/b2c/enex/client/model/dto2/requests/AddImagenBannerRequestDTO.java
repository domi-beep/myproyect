package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddImagenBannerRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	private String imgWeb;      
	private String imgMobile;   
	private String linkUrl;     
	private String target;      
	private LocalDateTime fechaInicio; 
	private LocalDateTime fechaTermino;
	private String estado;      
	private Long idBanner;   
	private String nombre;      
	private Boolean countdown;  
	private Long Web_B;
	private Long Mob_B;
	
	
}
