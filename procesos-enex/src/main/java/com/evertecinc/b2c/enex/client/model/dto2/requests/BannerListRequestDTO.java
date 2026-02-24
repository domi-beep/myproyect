package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.ImagenDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BannerListRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	private Long idBanner;
	private Long idGrupoBanner;
	private String codigo;
	private Long orden;
	private String estado;
	private Boolean borrado;
	private String nombre;
	private String codigoPortal;
	private String posicion;
	private List<ImagenDTO> imagen;
	private Integer start;      
	private String validaNulos;
}
