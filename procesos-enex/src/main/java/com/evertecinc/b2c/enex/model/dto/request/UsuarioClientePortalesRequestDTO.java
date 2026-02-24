package com.evertecinc.b2c.enex.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioClientePortalesRequestDTO {

	private Long idCliente;
	private Long idUsuario;
	private String portalUsuario;
}
