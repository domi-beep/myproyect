package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user", nullable = false)
	private Long idUser;

	@Column(name = "rut", length = 45)
	private String rut;

	@Column(name = "username", length = 45)
	private String username;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "first_name", length = 255)
	private String firstName;

	@Column(name = "last_name", length = 255)
	private String lastName;

	@Column(name = "role")
	private Integer role;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "num_retries", columnDefinition = "smallint default 0")
	private Short numRetries;

	@Column(name = "date_last_login")
	private LocalDateTime dateLastLogin;

	@Column(name = "status", length = 1, columnDefinition = "bpchar default 'A'")
	private String status;

	@Column(name = "newpass", columnDefinition = "smallint default 1")
	private Short newPass;

	@Column(name = "statuskc", length = 1, columnDefinition = "bpchar default 'P'")
	private String statuskc;

	@Column(name = "data", length = 1000)
	private String data;
	
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;
}
