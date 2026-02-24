package com.evertecinc.b2c.enex.client.model.dto2;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XmlRootElement(name = "CardChipInformation")
public class ResponseCardChipInformation {
	
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
	 * Fecha de expiración MM/AA
	 */
	private String validDate;
	
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
	 * Id vehículo
	 */
	private Integer uniqueID;

}
