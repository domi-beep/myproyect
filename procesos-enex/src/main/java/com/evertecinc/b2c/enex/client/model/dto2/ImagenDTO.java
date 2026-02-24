package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImagenDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("idImagen")
	private Long idImagen;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("estado")
	private String estado;

	@JsonProperty("fechaInicio")
	private LocalDateTime fechaInicio;

	@JsonProperty("fechaFin")
	private LocalDateTime fechaFin;

	@JsonProperty("nuevaVentana")
	private Boolean nuevaVentana;

	@JsonProperty("url")
	private String url;

	@JsonProperty("imagenWeb")
	private String imagenWeb;

	@JsonProperty("imagenMobile")
	private String imagenMobile;

	@JsonProperty("reloj")
	private Boolean reloj;

	@JsonProperty("orden")
	private Integer orden;
	
	@JsonProperty(value = "idVitrina", access = JsonProperty.Access.WRITE_ONLY)
	private Long idVitrina;

	public ImagenDTO() {
		super();
	}
	
	public ImagenDTO(Long idImagen) {
		super();
		this.idImagen = idImagen;
	}

	public ImagenDTO(Long idImagen, String nombre, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Boolean nuevaVentana, String url, String imagenWeb, String imagenMobile, Boolean reloj, Integer orden) {
		super();
		this.idImagen = idImagen;
		this.nombre = nombre;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nuevaVentana = nuevaVentana;
		this.url = url;
		this.imagenWeb = imagenWeb;
		this.imagenMobile = imagenMobile;
		this.reloj = reloj;
		this.orden = orden;
	}

}
