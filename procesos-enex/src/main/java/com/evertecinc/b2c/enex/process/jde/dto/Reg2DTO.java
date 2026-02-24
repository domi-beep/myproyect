package com.evertecinc.b2c.enex.process.jde.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reg2DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3456683625200316482L;
	
	private String tipoReg;
	private String idVenta;
	private String idCompra;
	private String tarjetas;
	private String numeroOC;

	
}
