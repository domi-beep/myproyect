package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JefeZonaDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 975953277027862963L;


	private Long idJefeZona;
	private String rut;
	private String name;
	private String phone;
	private String email;
	private String status;
	
    
}
