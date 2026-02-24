package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardUseDTO {
	
	@JsonProperty("idCardUse")
	private Long idCardUse;
	
	@JsonProperty("station")
	private String station;
	
	@JsonProperty("codeStation")
	private String codeStation;
	
	@JsonProperty("stationRut")
	private String stationRut;
	
	@JsonProperty("trx")
	private String trx;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dateTrx")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateTrx;
	
	@JsonProperty("clientId")
	private String clientId;
	
	@JsonProperty("clientRut")
	private String clientRut;
	
	@JsonProperty("deptId")
	private String deptId;
	
	@JsonProperty("deptName")
	private String deptName;
	
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
	
	@JsonProperty("cantidad")
	private Integer cantidad;
	
	@JsonProperty("area")
	private String area;
	
	@JsonProperty("region")
	private String region;
	
	@JsonProperty("performance")
	private BigDecimal performance;
	
	@JsonProperty("controlTotal")
	private Boolean controlTotal;
	
	@JsonProperty("datapass")
	private Boolean datapass;
	
	@JsonProperty("ctStation")
	private Boolean ctStation;
	
	@JsonProperty("tipoOdometro")
	private Boolean tipoOdometro;
	
	@JsonProperty("nombreEstacion")
	private String nombreEstacion;
	
	@JsonProperty("pumpUnitPrice")
	private BigDecimal pumpUnitPrice;
	
	@JsonProperty("latitud")
	private String latitud;
	
	@JsonProperty("longitud")
	private String longitud;
	
	@JsonProperty("nombreCiudad")
	private String nombreCiudad;
	
	@JsonProperty("nombreRegion")
	private String nombreRegion;

	public CardUseDTO(Long idCardUse, String station, String stationRut, String trx, LocalDateTime dateTrx,
			String clientId, String clientRut, String deptId, String deptName, String cardNumber, String plate,
			String productCode, String productName, BigDecimal unitPrice, BigDecimal quantity, BigDecimal totalPrice,
			String returnCode, String driverRut, String odometer, BigDecimal amountBalance, BigDecimal remainingLoads,
			String periodType, BigDecimal remainingAmount, String documentNumber, Boolean controlTotal,
			Boolean datapass, String nombreEstacion, String latitud, String longitud, String nombreCiudad,
			String nombreRegion) {
		super();
		this.idCardUse = idCardUse;
		this.station = station;
		this.stationRut = stationRut;
		this.trx = trx;
		this.dateTrx = dateTrx;
		this.clientId = clientId;
		this.clientRut = clientRut;
		this.deptId = deptId;
		this.deptName = deptName;
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
		this.controlTotal = controlTotal;
		this.datapass = datapass;
		this.nombreEstacion = nombreEstacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.nombreCiudad = nombreCiudad;
		this.nombreRegion = nombreRegion;
	}



	

}
