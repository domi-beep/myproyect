package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoUpdateUsuarioJsonRequestDTO {

	@JsonProperty("idUser")
	private Long idUser;

	@JsonProperty("name")
	private String name;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("newPass")
	private Short newPass;

	@JsonProperty("status")
	private String status;

	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@JsonProperty("rut")
	private String rut;

	@JsonProperty("passwordActual")
	private String passwordActual;

	@JsonProperty("password")
	private String password;

	@JsonProperty("Repassword")
	private String Repassword;

	@JsonProperty("role")
	private Long role;

	// ******************* PARA FINES DE SESSION *******************
	@JsonProperty("upiSession")
	private String upiSession;

	@JsonProperty("rutUsuarioSession")
	private String rutUsuarioSession;

	@JsonProperty("portal")
	private String portal;

	@JsonProperty("clientType")
	private String clientType;
	// ******************* PARA FINES DE SESSION FIN *******************

}
