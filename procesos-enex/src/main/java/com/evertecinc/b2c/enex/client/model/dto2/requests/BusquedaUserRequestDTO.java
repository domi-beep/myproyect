package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusquedaUserRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("idUser")
	private Long idUser;
	
	/**
	 * Rut empresa
	 */
	@JsonProperty("upi")
	private String upi;
	/**
	 * Id Cliente
	 */
	@JsonProperty("idClient")
	private Long idClient;
	/**
	 * Rut usuario
	 */
	@JsonProperty("rutUsuario")
	private String rutUsuario;

	@JsonProperty("rutUsuarioLogin")
	private String rutUsuarioLogin; // para relacion
	/**
	 * Nombre usuario
	 */
	@JsonProperty("nombre")
	private String nombre;
	/**
	 * Apellido paterno usuario
	 */
	@JsonProperty("apellidoPaterno")
	private String apellidoPaterno;
	/**
	 * Apellido materno usuario
	 */
	@JsonProperty("apellidoMaterno")
	private String apellidoMaterno;
	/**
	 * mail usuario
	 */
	@JsonProperty("email")
	private String email;
	/**
	 * estado usuario
	 * 
	 * 'A': Activo
	 * 'I': Inactivo
	 * 'E': Eliminado
	 */
	@JsonProperty("estado")
	private String estado;
	
	/**
	 * Perfil del usuario
	 */
	@JsonProperty("role")
	private Long role;
	
	/**
	 * Cantidad de registros
	 */
	@JsonProperty("cantidad")
	private Integer cantidad;
	
	/**
	 * Tipo de cliente: SCT, SCE, PRE
	 */
	@JsonProperty("tipoCliente")
	private String tipoCliente;
	
	@JsonProperty("idDepartamento")
	private Long idDepartamento;

	@JsonProperty("portal")
	private String portal;

}
