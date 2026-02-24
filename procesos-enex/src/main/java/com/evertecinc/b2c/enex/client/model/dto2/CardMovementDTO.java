package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardMovementDTO {

	
	@JsonProperty("idCardUse")
    private Long idCardUse;

    @JsonProperty("stationName")
    private String stationName;

    @JsonProperty("stationCode")
    private String stationCode;

    @JsonProperty("area")
    private String area;

    @JsonProperty("region")
    private String region;

    @JsonProperty("trx")
    private String trx;

    @JsonProperty("datetrx")
    private LocalDateTime datetrx;

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("clientRut")
    private String clientRut;

    @JsonProperty("deptId")
    private Integer deptId;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("plate")
    private String plate;

    @JsonProperty("productCode")
    private String productCode;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @JsonProperty("quantity")
    private BigDecimal quantity;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @JsonProperty("returnCode")
    private String returnCode;

    @JsonProperty("driverRut")
    private String driverRut;

    @JsonProperty("odometer")
    private String odometer;

    @JsonProperty("amountBalance")
    private BigDecimal amountBalance;

    @JsonProperty("remainingLoads")
    private BigDecimal remainingLoads;

    @JsonProperty("periodType")
    private String periodType;

    @JsonProperty("remainingAmount")
    private BigDecimal remainingAmount;

    @JsonProperty("documentNumber")
    private String documentNumber;

    @JsonProperty("performance")
    private BigDecimal performance;

    @JsonProperty("ct")
    private Integer ct;

    @JsonProperty("dataPass")
    private Integer dataPass;

    @JsonProperty("ctStation")
    private Integer ctStation;

    @JsonProperty("tipoCarga")
    private Integer tipoCarga;

    @JsonProperty("tipoOdometro")
    private Integer tipoOdometro;

    @JsonProperty("clientType")
    private String clientType;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("legalName")
    private String legalName;

    @JsonProperty("boardPrice")
    private BigDecimal boardPrice;

    @JsonProperty("nickName")
    private String nickName;
    
    @JsonProperty("descuento")
    private BigDecimal descuento;

    
    /*
     * getListCardMovementAllDeparment
     */
	public CardMovementDTO(Long idCardUse, String stationName, String stationCode, String area, String region,
			String trx, LocalDateTime datetrx, String clientId, String clientRut, Integer deptId, String cardNumber,
			String plate, String productCode, String productName, BigDecimal unitPrice, BigDecimal quantity,
			BigDecimal totalPrice, String returnCode, String driverRut, String odometer, BigDecimal amountBalance,
			BigDecimal remainingLoads, String periodType, BigDecimal remainingAmount, String documentNumber,
			BigDecimal performance, Integer ct, Integer dataPass, Integer ctStation, Integer tipoCarga,
			Integer tipoOdometro, String clientType, String documentType, String legalName, BigDecimal boardPrice,
			String nickName) {
		super();
		this.idCardUse = idCardUse;
		this.stationName = stationName;
		this.stationCode = stationCode;
		this.area = area;
		this.region = region;
		this.trx = trx;
		this.datetrx = datetrx;
		this.clientId = clientId;
		this.clientRut = clientRut;
		this.deptId = deptId;
		this.cardNumber = cardNumber;
		this.plate = plate;
		this.productCode = productCode;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.returnCode = returnCode;
		this.driverRut = driverRut;
		this.odometer = odometer;
		this.amountBalance = amountBalance;
		this.remainingLoads = remainingLoads;
		this.periodType = periodType;
		this.remainingAmount = remainingAmount;
		this.documentNumber = documentNumber;
		this.performance = performance;
		this.ct = ct;
		this.dataPass = dataPass;
		this.ctStation = ctStation;
		this.tipoCarga = tipoCarga;
		this.tipoOdometro = tipoOdometro;
		this.clientType = clientType;
		this.documentType = documentType;
		this.legalName = legalName;
		this.boardPrice = boardPrice;
		this.nickName = nickName;
	}


	public CardMovementDTO() {
	}


	/**
	 * @param idCardUse
	 * @param stationName
	 * @param stationCode
	 * @param area
	 * @param region
	 * @param trx
	 * @param datetrx
	 * @param clientId
	 * @param clientRut
	 * @param deptId
	 * @param cardNumber
	 * @param plate
	 * @param productCode
	 * @param productName
	 * @param unitPrice
	 * @param quantity
	 * @param totalPrice
	 * @param returnCode
	 * @param driverRut
	 * @param odometer
	 * @param amountBalance
	 * @param remainingLoads
	 * @param periodType
	 * @param remainingAmount
	 * @param documentNumber
	 * @param performance
	 * @param ct
	 * @param dataPass
	 * @param ctStation
	 * @param tipoCarga
	 * @param tipoOdometro
	 * @param clientType
	 * @param documentType
	 * @param legalName
	 * @param boardPrice
	 * @param nickName
	 */
//	public CardMovementDTO(Long idCardUse, String stationName, String stationCode, String area, String region,
//			String trx, LocalDateTime datetrx, String clientId, String clientRut, Integer deptId, String cardNumber,
//			String plate, String productCode, String productName, BigDecimal unitPrice, BigDecimal quantity,
//			BigDecimal totalPrice, String returnCode, String driverRut, String odometer, BigDecimal amountBalance,
//			BigDecimal remainingLoads, String periodType, BigDecimal remainingAmount, String documentNumber,
//			BigDecimal performance, Integer ct, Integer dataPass, Integer ctStation, Integer tipoCarga,
//			Integer tipoOdometro, String clientType, String documentType, String legalName, BigDecimal boardPrice,
//			String nickName) {
//		super();
//		this.idCardUse = idCardUse;
//		this.stationName = stationName;
//		this.stationCode = stationCode;
//		this.area = area;
//		this.region = region;
//		this.trx = trx;
//		this.datetrx = datetrx;
//		this.clientId = clientId;
//		this.clientRut = clientRut;
//		this.deptId = deptId;
//		this.cardNumber = cardNumber;
//		this.plate = plate;
//		this.productCode = productCode;
//		this.productName = productName;
//		this.unitPrice = unitPrice;
//		this.quantity = quantity;
//		this.totalPrice = totalPrice;
//		this.returnCode = returnCode;
//		this.driverRut = driverRut;
//		this.odometer = odometer;
//		this.amountBalance = amountBalance;
//		this.remainingLoads = remainingLoads;
//		this.periodType = periodType;
//		this.remainingAmount = remainingAmount;
//		this.documentNumber = documentNumber;
//		this.performance = performance;
//		this.ct = ct;
//		this.dataPass = dataPass;
//		this.ctStation = ctStation;
//		this.tipoCarga = tipoCarga;
//		this.tipoOdometro = tipoOdometro;
//		this.clientType = clientType;
//		this.documentType = documentType;
//		this.legalName = legalName;
//		this.boardPrice = boardPrice;
//		this.nickName = nickName;
//	}


	
	
	
	
	
	
	
	
	
	
    
    
    
	
	
}
