package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBalanceDepartmentDTO {
	
	private Long    	idDepartment;
	private String  	idOrpakDepartment;
	private BigDecimal  balance;
	private String 		balanceType;
	private String 		code;
	private String		description;
	private String		stockType;

}
