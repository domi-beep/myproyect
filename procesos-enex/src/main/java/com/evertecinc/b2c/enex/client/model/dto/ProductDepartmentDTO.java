package com.evertecinc.b2c.enex.client.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDepartmentDTO {
	
	private Long idDepartment;
	private String productCode;
	private BigDecimal remainingAmount;
	private String documentType;

}
