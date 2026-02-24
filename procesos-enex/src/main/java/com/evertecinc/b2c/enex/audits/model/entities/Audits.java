package com.evertecinc.b2c.enex.audits.model.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "audits")
@Data
@NoArgsConstructor
public class Audits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_audit", nullable = false)
	private Long idAudit;

	@Column(name = "insert_login", length = 200)
	private String insertLogin;

	@Column(name = "insert_name", length = 200)
	private String insertName;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "action", length = 100)
	private String action;

	@Column(name = "description", length = 1500)
	private String description;

	@Column(name = "system", length = 200)
	private String system;
	
	@Column(name = "id_elemento")
	private Long idElemento;
	
	@Column(name = "tipo_elemento",length = 100)
	private String tipoElemento;
}
