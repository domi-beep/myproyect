package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.requests.ProductsDepartmentsByCriteriaRequestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDepartmentDTO {
	
	private Long idDepartment;
	private String productCode;
	private BigDecimal remainingAmount;
	private String documentType;
	/**
	 * Descripcion del movimiento motivo de esta actualizacion de saldo
	 */
	private String movimiento;
	/**
	 * Usuario que realizo la modificacion de saldo
	 */
	private String username;
	
	public ProductsDepartmentsByCriteriaRequestDTO toProductsDepartmentsByCriteriaRequestDTO() {
		ProductsDepartmentsByCriteriaRequestDTO dto = new ProductsDepartmentsByCriteriaRequestDTO();
		dto.setIdDepartment(this.idDepartment);
		dto.setProductCode(this.productCode);
		dto.setRemainingAmount(this.remainingAmount);
		dto.setDocumentType(this.documentType);
		
		return dto;
	}

}
