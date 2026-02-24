package com.evertecinc.b2c.enex.email.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailConfigDTO {
	
	/**
	 * Identificador �nico
	 */
	private Long idEmail;
	/**
	 * Texto presentado en weblist
	 */
	private String title;
	/**
	 * Email para enviar correo
	 */
	private String email;
	/**
	 * Titulo del email
	 */
	private String subject;
	/**
	 * Estado 
	 * A: activo,
	 * I: inactivo
	 */
	private String status;
	
	/**
	 * @return the idEmail
	 */
	public Long getIdEmail() {
		return idEmail;
	}
	/**
	 * @param idEmail the idEmail to set
	 */
	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
