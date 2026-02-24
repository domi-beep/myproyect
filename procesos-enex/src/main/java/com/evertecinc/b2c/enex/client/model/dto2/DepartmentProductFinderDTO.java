package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentProductFinderDTO {
	
	private Long idDepartment;
	private String nameDepartment;
	private String productCode;
	private BigDecimal remainingAmount;
	private String documentType;
	private String nameProduct;
	private String statusProduct;
	private String typeBalance;
	private Boolean typeBalanceEnProceso;
	
	/**
	 * doListProductDepartment
	 */
	public DepartmentProductFinderDTO(Long idDepartment, String nameDepartment, String productCode,
			BigDecimal remainingAmount, String documentType, String nameProduct, String statusProduct,
			String typeBalance) {
		super();
		this.idDepartment = idDepartment;
		this.nameDepartment = nameDepartment;
		this.productCode = productCode;
		this.remainingAmount = remainingAmount;
		this.documentType = documentType;
		this.nameProduct = nameProduct;
		this.statusProduct = statusProduct;
		this.typeBalance = typeBalance;
	}
	
	

}
