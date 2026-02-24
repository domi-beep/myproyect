package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShopCartDTO {
	
	/**
	 * Identificador unico
	 */
	private Long idShopCart;
	/**
	 * Id cliente
	 */
	private Long idClient;
	/**
	 * Id usuario
	 */
	private Long idUser;
	/**
	 * combustible
	 */
	private String productCode;
	/**
	 * id tarjeta
	 */
	private Long idCard;
	/**
	 * id departamento
	 */
	private Long idDepartment;
	/**
	 * Monto compra
	 */
	private BigDecimal amount;
	
	/**
	 * Tipo de documento
	 */
	private String documentType;
	
	/**
	 * id documento
	 */
	private String idDocumento;
	/**
	 * numero documento
	 */
	private String numDocumento;
	/**
	 * tipo documento JDE
	 */
	private String tipoDocumento;
	
	private String nombreProducto;
	private String nombreDepto;
	private String patente;
	private String nickname;
	

}
