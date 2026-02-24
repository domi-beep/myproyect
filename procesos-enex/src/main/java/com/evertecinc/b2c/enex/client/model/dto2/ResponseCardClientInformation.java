package com.evertecinc.b2c.enex.client.model.dto2;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseCardClientInformation {
	
	/**
	 * Número de placa patente 
	 */
	private String cardPlate;
	
	/**
	 * Número de la tarjeta (bean)
	 */
	private String cardNumber;
	
	/**
	 * XML que se recibió desde ORPAK
	 */
	private String xmlOrpak;
	
	/**
	 * Nombre empresa
	 */
	private String companyName;
	
	/**
	 * Rut empresa
	 */
	private String companyRUT;

	/**
	 * Tipo de documento
	 */
	private String documentType;
	
	/**
	 * Código de producto
	 */
	private String productCode;
	
	/**
	 * Tipo de vehículo
	 */
	private String vehicleType;
	
	/**
	 * Fecha de expiración MM/AA
	 */
	private String validDate;

	private String direccion;
	private String comuna;
	private String region;
	private String ciudad;
	private String tipoImpresion;
	private String tipoReglamento;
	private String clientStatus;
	private Integer uniqueID;
	private String cardType;

}
