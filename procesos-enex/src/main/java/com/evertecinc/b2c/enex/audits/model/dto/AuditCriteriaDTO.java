package com.evertecinc.b2c.enex.audits.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditCriteriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7961721909883998809L;
	private Long idAudit;
    private String insertLogin;
    private String insertName;
    private String modifyLogin;
    private String modifyName;
    private LocalDateTime dateFirst;
    private LocalDateTime dateEnd;
    private String action;
    private String description;
    private String system;

}
