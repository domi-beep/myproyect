package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConductorDTO {

	private Long idConductor;
	private Long idCliente;
	private String nombre;
	private String rut;
	private String telefono;
	private String estado;
	
	public ConductorDTO(Long idConductor, Long idCliente, String nombre, String rut, String telefono, String estado) {
		super();
		this.idConductor = idConductor;
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.rut = rut;
		this.telefono = telefono;
		this.estado = estado;
	}
}
