package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	
	/**
	 * Id usuario
	 */
	private Long idUser;
	/**
	 * Rut usuario
	 */
	private String rut;
	/**
	 * Nombre de usuario para ingresar al sistema
	 */
	private String username;
	/**
	 * Nombre del usuario
	 */
	private String name;
	/**
	 * Apellido Paterno del usuario
	 */
	private String firstName;
	/**
	 * Apellido Materno del usuario
	 */
	private String lastName;
	/**
	 * Rol del usuario en el sistema.
	 */
	private Long role;
	/**
	 * Correo electronico del usuario.
	 */
	private String email;
	/**
	 * Contraseña para ingresar al sistema.
	 */
	private String password;
	/**
	 * Para escribir nuevamente la contraseña.
	 */
	private String retypePassword;
	/**
	 * Contraseña actual existente en el sistema.
	 */
	private String passwordActual;
	/**
	 * Número de entradas al sistema.
	 */
	private Long numEntries;
	/**
	 * Fecha de última conexión al sistema.
	 */
	private LocalDateTime dateLastLogin;
	/**
	 * Status del usuario
	 */
	private String status;
	/**
	 * Nueva contraseña del usuario.
	 */
	private Long newPass;
	/**
	 * Identifica si usuario es administrador del sistema.
	 */
	private Boolean isAdmin;
	
	/**
	 * Cantidad de registros
	 */
	private Integer cantidad;
	
	private String tipoCliente;
	
	private String descripcionTipoCliente;
	
	private String nombreTecnico;
	private Long idDepartamento;
	private Long idCliente;
	/**
	 * @param idUser
	 * @param rut
	 * @param username
	 * @param name
	 * @param firstName
	 * @param lastName
	 * @param role
	 * @param email
	 * @param password
	 * @param status
	 * @param tipoCliente
	 */
	public UserDTO(Long idUser, String rut, String username, String name, String firstName, String lastName, Long role,
			String email, String password, String status, String tipoCliente, Long idClient) {
		super();
		this.idUser = idUser;
		this.rut = rut;
		this.username = username;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.password = password;
		this.status = status;
		this.tipoCliente = tipoCliente;
		this.idCliente = idClient;
	}

	public UserDTO(Long idUser, String rut, String username, String name, String firstName, String lastName, Long role,
			String email, String password, String status, String tipoCliente) {
		super();
		this.idUser = idUser;
		this.rut = rut;
		this.username = username;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.password = password;
		this.status = status;
		this.tipoCliente = tipoCliente;
	}
	
	/**
	 * @param idUser
	 * @param name
	 * @param firstName
	 * @param lastName
	 * @param role
	 * 
	 * @apiNote Method: getListUserByDepartment
	 * 
	 */
	public UserDTO(Long idUser, String name, String firstName, String lastName, Long role) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	
	
	

}
