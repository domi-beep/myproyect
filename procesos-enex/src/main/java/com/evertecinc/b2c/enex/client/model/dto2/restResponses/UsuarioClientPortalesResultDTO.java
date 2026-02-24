package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import com.evertecinc.b2c.enex.client.model.entities.Clients;
import com.evertecinc.b2c.enex.client.model.entities.Users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioClientPortalesResultDTO {
	
	private String portalUsuario;
	private String portalUsuarioCompleto;
	private Users usuario;
	private Clients cliente;
	private String colorPortal;
	private String urlImagenesS3;
	private String urlPortalFrontend;

}
