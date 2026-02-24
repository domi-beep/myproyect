package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtItemDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3667789205340246106L;

	private Long idItem;
	private Long idOt;
	private String dispositivo;
	private String posicion;
	private String numDispositivo;
	private Boolean instalacion;
	private Boolean reemplazo;
	private String tipoInstalacion;
	private String tipoFalla;
	private LocalDateTime fechaInstalacion;
	private String observacion;
	private LocalDateTime fechaOT;
	
}
