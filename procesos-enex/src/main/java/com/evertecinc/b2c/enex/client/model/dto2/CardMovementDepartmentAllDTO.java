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
public class CardMovementDepartmentAllDTO {

	@JsonProperty("idMov")
	private Long idMov;

	@JsonProperty("idCard")
	private Long idCard;

	@JsonProperty("idVehicle")
	private Long idVehicle;

	@JsonProperty("idClient")
	private Long idClient;

	@JsonProperty("movement")
	private String movement;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dateIn")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dateEnd")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateEnd;

	@JsonProperty("plate")
	private String plate;

	@JsonProperty("rutDriver")
	private String rutDriver;

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("monto")
	private BigDecimal monto;

	@JsonProperty("username")
	private String username;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("nameDepartment")
	private String nameDepartment;

	@JsonProperty("cantidad")
	private Integer cantidad;

	@JsonProperty("stationCode")
	private String stationCode;

	@JsonProperty("stationName")
	private String stationName;

	@JsonProperty("payType")
	private String payType;

	@JsonProperty("productCode")
	private String productCode;

	@JsonProperty("productName")
	private String productName;

	@JsonProperty("unitPrice")
	private BigDecimal unitPrice;

	@JsonProperty("quantity")
	private BigDecimal quantity;

	@JsonProperty("clientIdOrpak")
	private String clientIdOrpak;

	@JsonProperty("clientRut")
	private String clientRut;

	@JsonProperty("deptId")
	private String deptId;

	@JsonProperty("cardNumber")
	private String cardNumber;

	@JsonProperty("trx")
	private String trx;

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

	@JsonProperty("nickname")
	private String nickname;

	@JsonProperty("comuna")
	private String comuna;

	@JsonProperty("version")
	private String version;

	@JsonProperty("performance")
	private BigDecimal performance;

	@JsonProperty("controlPass")
	private Integer controlPass;

	@JsonProperty("dataPass")
	private Integer dataPass;

	@JsonProperty("returnCode")
	private String returnCode;

	@JsonProperty("area")
	private String area;

	@JsonProperty("region")
	private String region;

	@JsonProperty("ctStation")
	private Boolean ctStation;

	@JsonProperty("tipoCarga")
	private Boolean tipoCarga;

	@JsonProperty("tipoOdometro")
	private Boolean tipoOdometro;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("documentType")
	private String documentType;

	@JsonProperty("legalName")
	private String legalName;

	@JsonProperty("idRef")
	private Long idRef;

	@JsonProperty("referencia")
	private String referencia;

	@JsonProperty("boardPrice")
	private BigDecimal boardPrice;

	@JsonProperty("descuento")
	private BigDecimal descuento;

	@JsonProperty("dp")
	private String dp;

	@JsonProperty("ct")
	private String ct;

	@JsonProperty("station")
	private String station;

	public CardMovementDepartmentAllDTO(Long idMov, Long idCard, String movement, LocalDateTime dateIn, String plate,
		BigDecimal monto, String username, Long idDepartment, String nameDepartment, String stationCode,
		String stationName, String payType, String productCode, BigDecimal unitPrice, BigDecimal quantity,
		String driverRut, String odometer, String documentNumber, String nickname, String comuna, String version,
		BigDecimal performance, Integer controlPass, Integer dataPass, String clientType, String documentType,
		Long idRef, BigDecimal boardPrice) {
		
		super();
		this.idMov = idMov;
		this.idCard = idCard;
		this.movement = movement;
		this.dateIn = dateIn;
		this.plate = plate;
		this.monto = monto;
		this.username = username;
		this.idDepartment = idDepartment;
		this.nameDepartment = nameDepartment;
		this.stationCode = stationCode;
		this.stationName = stationName;
		this.payType = payType;
		this.productCode = productCode;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.driverRut = driverRut;
		this.odometer = odometer;
		this.documentNumber = documentNumber;
		this.nickname = nickname;
		this.comuna = comuna;
		this.version = version;
		this.performance = performance;
		this.controlPass = controlPass;
		this.dataPass = dataPass;
		this.clientType = clientType;
		this.documentType = documentType;
		this.idRef = idRef;
		this.boardPrice = boardPrice;
	}

		public CardMovementDepartmentAllDTO() {
			super();
		}

		public CardMovementDepartmentAllDTO(Long idMov) {
			super();
			this.idMov = idMov;
		}
}