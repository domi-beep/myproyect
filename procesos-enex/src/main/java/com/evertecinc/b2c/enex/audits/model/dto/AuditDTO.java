package com.evertecinc.b2c.enex.audits.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2165918763229504338L;
	private Long idAudit;
    private String insertLogin;
    private String insertName;
    private LocalDateTime date;
    private String action;
    private String description;
    private String system;
	private Long idElemento;
	private String tipoElemento;

}
