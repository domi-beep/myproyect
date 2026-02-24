package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoAgregarUsuarioJsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("nombre")
	private String name;
	
	@JsonProperty("apellidoPaterno")
	private String firstName;
	
	@JsonProperty("apellidoMaterno")
	private String lastName;
	
	@JsonProperty("email")
	private String mail;
	
	@JsonProperty("rutUsuario")
	private String rut;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("retypePassword")
	private String retypePassword;
	
	@JsonProperty("idCliente")
	private Long idCliente;
	
	private String usernameJWT;
	
	private String emailJWT;

}
