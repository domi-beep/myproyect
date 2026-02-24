package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImagenCriterioDTO {
	
	private Long idImagen;
	private Long idBanner;
	private String nombre;
	private String estado;
	private Boolean isVigente;
	private String ordenamiento;
	private String tipo;

}
