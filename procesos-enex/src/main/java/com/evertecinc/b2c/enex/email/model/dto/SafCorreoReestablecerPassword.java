package com.evertecinc.b2c.enex.email.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SafCorreoReestablecerPassword {
	
	private Long idCliente;
	private Long idUsuario;
	private String portalUsuario;

}
