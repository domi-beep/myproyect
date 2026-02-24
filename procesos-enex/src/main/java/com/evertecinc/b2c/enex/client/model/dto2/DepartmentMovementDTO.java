package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepartmentMovementDTO {

	private Long idMov;
	private Long idDepartment;
	private String nameDepartment;
	private String movement;
	private LocalDateTime dateIn;
	private String documentType;
	private String productCode;
	private String productName;
	private BigDecimal monto;
	private String username;
	private Long cantidad;
	private Long idRef;
	private String referencia;
	private String typeBalance;
	
	
	/**
	 * @param idMov
	 * @param idDepartment
	 * @param nameDepartment
	 * @param movement
	 * @param dateIn
	 * @param documentType
	 * @param productName
	 * @param monto
	 * @param username
	 * @param idRef
	 */
	public DepartmentMovementDTO(Long idMov, Long idDepartment, String nameDepartment, String movement,
			LocalDateTime dateIn, String documentType, String productName, BigDecimal monto, String username, Long idRef) {
		super();
		this.idMov = idMov;
		this.idDepartment = idDepartment;
		this.nameDepartment = nameDepartment;
		this.movement = movement;
		this.dateIn = dateIn;
		this.documentType = documentType;
		this.productName = productName;
		this.monto = monto;
		this.username = username;
		this.idRef = idRef;
	}
	
	
}
