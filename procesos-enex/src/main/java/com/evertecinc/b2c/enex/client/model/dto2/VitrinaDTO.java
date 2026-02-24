package com.evertecinc.b2c.enex.client.model.dto2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class VitrinaDTO {

	@JsonProperty("idVitrina")
	private Long idVitrina;

	@JsonProperty("codigo")
	private String codigo;

	@JsonProperty("estado")
	private String estado;

	@JsonProperty("borrado")
	private Boolean borrado;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("portal")
	private String portal;
	
	@JsonProperty("listaImagenes")
	private List<ImagenDTO> listaImagenes;

	public VitrinaDTO(Long idVitrina, String codigo, String estado, Boolean borrado, String nombre, String portal) {
		super();
		this.idVitrina = idVitrina;
		this.codigo = codigo;
		this.estado = estado;
		this.borrado = borrado;
		this.nombre = nombre;
		this.portal = portal;
	}
	
	
}
