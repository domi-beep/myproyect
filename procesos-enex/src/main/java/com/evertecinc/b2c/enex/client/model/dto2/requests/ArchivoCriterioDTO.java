package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ArchivoCriterioDTO {
	
	private Long idArchivo;
	private Long idGrupo;
	private Long idElemento;
	private Long idProducto;
	private String nombre;
	private String tipo;
	private String tipoElemento;
	private Integer orden;
	private String tamano;
	private String url;
	private String urlSinDominio;
	private byte[] imagenSource;
	private String path;
	private String contentType;
	private String extension;
	private String detalleProceso;
	private boolean seteado;
	private Boolean requerido = false;
	private String estado;
	private LocalDateTime fechainicio;
	private LocalDateTime fechafin;
	private Boolean reloj;
	private String dominio;

}
