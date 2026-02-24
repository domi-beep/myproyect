package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductsDepartmentsByCriteriaRequestDTO {
	
	private Long idDepartment;
	
	private String productCode;
	
	private BigDecimal remainingAmount;
	
	private String documentType;
	
	public ProductDepartmentDTO toProductDepartmentDTO() {
		ProductDepartmentDTO dto = new ProductDepartmentDTO();
		dto.setIdDepartment(this.idDepartment);
		dto.setProductCode(this.productCode);
		dto.setRemainingAmount(this.remainingAmount);
		dto.setDocumentType(this.documentType);
		
		return dto;
	}

}
