package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.ImagenDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BannerCriterioDTO {

	private Long idBanner;
	private Integer idGrupoBanner;
	private String codigoGrupoBanner;
	private String codigo;
	private String nombre;
	private String estado;
	private Long orden;
	private String tipo;
	private ImagenDTO imagen;
	private List<String> listCodigoPortal;
	private String posicion;
}
