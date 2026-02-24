package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjecutivoDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3060209268638139471L;

	private Long idEjecutivo;
	private String rut;
	private String name;
	private String phone;
	private String email;
	private String status;

    
}
