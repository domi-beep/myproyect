package com.evertecinc.b2c.enex.client.model.dto2;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BannerDTO {
	
	private Integer idBanner;
	private Integer idGrupoBanner;
	private String codigo;
	private Integer orden;
	private String estado;
	private Boolean borrado;
	private String nombre;
	private String codigoPortal;
	private String posicion;
	private List<ImagenDTO> imagen;

}
