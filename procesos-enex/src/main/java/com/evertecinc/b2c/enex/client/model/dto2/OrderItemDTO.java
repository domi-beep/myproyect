package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDTO {
	
	/**
	 * Identificador único del item de la orden
	 */
	private Long idOrderItem;
	/**
	 * Identificador único de la orden
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
	 * Identificador único de la tarjeta
	 */
	private Long idCard;
	/**
	 * Identificador único del departamento
	 */
	private Long idDepartment;
	/**
	 * Identificador único del vehiculo
	 */
	private Long idVehicle;
	/**
	 * Identificador único del producto
	 */
	private String productCode;
	/**
	 * Monto de la orden
	 */
	private BigDecimal amount;
	/**
	 * Tipo de documento
	 */
	private String documentType;
	
	/**
	 * Nombre del departamento asociado
	 */
	private String nombreDepto;
	
	/**
	 * Nombre del combustible
	 */
	private String nombreProducto;
	
	/**
	 * Nickname asociado al vehiculo
	 */
	private String nickname;
	
	/**
	 * Patente asociada a vehiculo
	 */
	private String patente;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String firstName;
	/**
	 * id documento (credito)
	 */
	private String idDocumento;
	/**
	 * numero de documento (credito)
	 */
	private String numeroDocumento;
	/**
	 * tipo de documento (credito)
	 */
	private String tipoDocumento;

}
