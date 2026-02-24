package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OcItemDTO {
	/**
	 * Identificador único del item
	 */
	private Long idItem;
	/**
	 * Identificador único de la oc
	 */
	private Long idOrder;
	/**
	 * Identificador único del cliente
	 */
	private Long idClient;
	/**
	 * Identificador único del usuario
	 */
	private Long idUser;
	/**
	 * Identificador único del departamento
	 */
	private Long idDepartment;
	/**
	 * Identificador único del producto
	 */
	private String productCode;
	/**
	 * Monto de producto
	 */
	private BigDecimal amount;
	/**
	 * Nombre del departamento asociado
	 */
	private String nombreDepto;
	/**
	 * Nombre del combustible
	 */
	private String nombreProducto;

	private String docType;
}
