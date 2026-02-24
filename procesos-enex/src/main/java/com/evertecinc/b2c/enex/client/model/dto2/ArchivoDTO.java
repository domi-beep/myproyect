package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoDTO extends CriterioBusquedaGenericoDTO {

	private Long idArchivo;
	private String 			nombre;
	private String 			tipo;
	private String 			tamano;
	private String 			url;
	private Long 			idElemento;
	private String 			tipoElemento;
	private Integer 		orden;
	private String 			path;
	private Integer 		idGrupo;
	private String 			estado;
	private LocalDateTime 	fechaInicio;
	private LocalDateTime 	fechaFin;
	private Integer 		reloj;

	private byte[] 			imagenSource;
	private String 			extension;
	private String 			contentType;

	private String 			portal;
	
}
