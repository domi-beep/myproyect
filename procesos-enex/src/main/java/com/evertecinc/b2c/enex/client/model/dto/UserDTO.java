package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 109428208595710380L;
	
	private Long idUser;
	private String rut;
	private String username;
	private String name;
	private String firstName;
	private String lastName;
	private Long role;
	private String email;
	private String password;
	private String retypePassword;
	private String passwordActual;
	private Long numEntries;
	private LocalDateTime dateLastLogin;
	private String status;
	private Long newPass;
	private Boolean isAdmin;
	private Integer cantidad;
	
	private String tipoCliente;
	
	private String descripcionTipoCliente;
	
	private String nombreTecnico;
	private Long idDepartamento;
	private Long idCliente;

}
