package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MonitorCardDTO {
	/**
	 * Id Tarjeta
	 */
	private Long idCard;
	/**
	 * Id departamento
	 */
	private Long idDepartment;
	/**
	 * Id cliente
	 */
	private Long idClient;
	/**
	 * Nombre de departamento
	 */
	private String nameDepto;
	/**
	 * Tipo de restricción
	 */
	private String restrictionType;

	private String restrType;
	/**
	 * Manejo de saldo C: Tarjeta D: Departamento
	 */
	private String typeBalance;
	/**
	 * Id vehiculo
	 */
	private Long idVehicle;

	/*
	 * A: activo; I:inactico; E:eliminado
	 */
	private String vehicleStatus;
	/**
	 * Número de tarjeta
	 */
	private String cardnum;
	/**
	 * Saldo tarjeta
	 */
	private BigDecimal remainingAmount;
	/**
	 * Patente del vehiculo
	 */
	private String plate;
	/**
	 * Nombre de usuario
	 */
	private String nickname;
	/**
	 * Estado tarjeta
	 */
	private String cardStatus;

	/**
	 * Codigo combustible
	 */
	private String productCode;

	/**
	 * Nombre combustible usado por vehiculo.
	 */
	private String productName;

	/**
	 * Tipo de documento: Boleta, Factura
	 */
	private String documentType;

	/**
	 * Tipo de vehiculo
	 */
	private String vehicleTypeName;

	/**
	 * Cantidad de registros
	 */
	private Integer cantidad;

	/**
	 * Indica el estado se la solicitud de numero de tarjeta para el vehiculo
	 */
	private String reqCardStatus;

	/**
	 * Estado del departamento al que pertenece esta tarjeta
	 */
	private String departmentStatus;

	private Boolean reqCardReprint;

	private String version;


	private Long remainingTrxLoad;

	private BigDecimal remainingPeriodAmount;

	private Boolean controlTotal;

	private Boolean datapass;

	private Boolean datachip;

	private BigDecimal litros;

	private String ctPosition;
	
	// Dias de carga
	private Integer restrL;
	private Integer restrM;
	private Integer restrX;
	private Integer restrJ;
	private Integer restrV;
	private Integer restrS;
	private Integer restrD;
	
	// Horarios de carga
	private Integer restrHini;
	private Integer restrHend;
	
	// Carga
	private BigDecimal restrAmountMax; // litros max a cargar por restriccion
	private Integer restrDailyMaxLoads; // count max de cargas por restriccion
	private Integer restrDailyMaxQuantity; // cant max de litros por cada carga
	
	private Boolean hasReprintSaf;
	
	// validacion de campo GPS cliente y vehiculo (PARA CONTROL TOTAL)
//	private Boolean validaciongpsDisabled;
//	private Boolean clientegps;
//	private Boolean validaciongpsChecked;
//	
//	private String clientGps;
//	private String vehicleGps;
	
	private String warningStockChannel;
	private String warningLoadChannel;
	private String warningStock;
	private String warningStockEmail;
	private String warningLoadEmail;

	/**
	 * Constructor que se utiliza en el extended TarjetaRepoImpl en el metodo getListCardsByCriterio
	 */
	public MonitorCardDTO(Long idCard, Long idDepartment, String nameDepto, String restrictionType, String restrType, String typeBalance,
			Long idVehicle, String vehicleStatus, String cardnum, BigDecimal remainingAmount, String plate,
			String nickname, String cardStatus, String productCode, String productName, String documentType,
			String vehicleTypeName, String reqCardStatus, String departmentStatus, boolean reqCardReprint, String version,
			Long remainingTrxLoad, BigDecimal remainingPeriodAmount,
			Boolean controlTotal, Boolean datapass, BigDecimal litros, String ctPosition, Long idClient, Integer restrL,
			Integer restrM, Integer restrX, Integer restrJ, Integer restrV, Integer restrS, Integer restrD,
			Integer restrHini, Integer restrHend, BigDecimal restrAmountMax, Integer restrDailyMaxLoads,
			Integer restrDailyMaxQuantity, Boolean hasReprintSaf, Boolean datachip, String warningStockChannel, String warningLoadChannel,
			String warningStock,
			String warningStockEmail,
			String warningLoadEmail
			) {
		super();
		this.idCard = idCard;
		this.idDepartment = idDepartment;
		this.nameDepto = nameDepto;
		this.restrictionType = restrictionType;
		this.restrType = restrType;
		this.typeBalance = typeBalance;
		this.idVehicle = idVehicle;
		this.vehicleStatus = vehicleStatus;
		this.cardnum = cardnum;
		this.remainingAmount = remainingAmount;
		this.plate = plate;
		this.nickname = nickname;
		this.cardStatus = cardStatus;
		this.productCode = productCode;
		this.productName = productName;
		this.documentType = documentType;
		this.vehicleTypeName = vehicleTypeName;
		this.reqCardStatus = reqCardStatus;
		this.departmentStatus = departmentStatus;
		this.reqCardReprint = reqCardReprint;
		this.version = version;
		this.remainingTrxLoad = remainingTrxLoad;
		this.remainingPeriodAmount = remainingPeriodAmount;
		this.controlTotal = controlTotal;
		this.datapass = datapass;
		this.litros = litros;
		this.ctPosition = ctPosition;
		this.idClient = idClient;
		this.restrL = restrL;
		this.restrM = restrM;
		this.restrX = restrX;
		this.restrJ = restrJ;
		this.restrV = restrV;
		this.restrS = restrS;
		this.restrD = restrD;
		this.restrHini = restrHini;
		this.restrHend = restrHend;

		this.restrAmountMax = restrAmountMax;
		this.restrDailyMaxLoads = restrDailyMaxLoads;
		this.restrDailyMaxQuantity = restrDailyMaxQuantity;
		
		this.hasReprintSaf = hasReprintSaf;
		
		this.datachip = datachip;
		
//		this.clientGps = clientGps;
//		this.vehicleGps = vehicleGps;
		
		this.warningStockChannel = warningStockChannel;
		this.warningLoadChannel = warningLoadChannel;
		this.warningStock = warningStock;
		this.warningStockEmail = warningStockEmail;
		this.warningLoadEmail = warningLoadEmail;

	}

	public String toStringNotNull() {
		return "MonitorCardDTO [" + (idCard != null ? "idCard=" + idCard + ", " : "")
				+ (idDepartment != null ? "idDepartment=" + idDepartment + ", " : "")
				+ (idClient != null ? "idClient=" + idClient + ", " : "")
				+ (nameDepto != null ? "nameDepto=" + nameDepto + ", " : "")
				+ (restrictionType != null ? "restrictionType=" + restrictionType + ", " : "")
				+ (restrType != null ? "restrType=" + restrType + ", " : "")
				+ (typeBalance != null ? "typeBalance=" + typeBalance + ", " : "")
				+ (idVehicle != null ? "idVehicle=" + idVehicle + ", " : "")
				+ (vehicleStatus != null ? "vehicleStatus=" + vehicleStatus + ", " : "")
				+ (cardnum != null ? "cardnum=" + cardnum + ", " : "")
				+ (remainingAmount != null ? "remainingAmount=" + remainingAmount + ", " : "")
				+ (plate != null ? "plate=" + plate + ", " : "")
				+ (nickname != null ? "nickname=" + nickname + ", " : "")
				+ (cardStatus != null ? "cardStatus=" + cardStatus + ", " : "")
				+ (productCode != null ? "productCode=" + productCode + ", " : "")
				+ (productName != null ? "productName=" + productName + ", " : "")
				+ (documentType != null ? "documentType=" + documentType + ", " : "")
				+ (vehicleTypeName != null ? "vehicleTypeName=" + vehicleTypeName + ", " : "")
				+ (cantidad != null ? "cantidad=" + cantidad + ", " : "")
				+ (reqCardStatus != null ? "reqCardStatus=" + reqCardStatus + ", " : "")
				+ (departmentStatus != null ? "departmentStatus=" + departmentStatus + ", " : "")
				+ (reqCardReprint != null ? "reqCardReprint=" + reqCardReprint + ", " : "")
				+ (version != null ? "version=" + version + ", " : "")
				+ (restrDailyMaxQuantity != null ? "restrDailyMaxQuantity=" + restrDailyMaxQuantity + ", " : "")
				+ (remainingTrxLoad != null ? "remainingTrxLoad=" + remainingTrxLoad + ", " : "")
				+ (remainingPeriodAmount != null ? "remainingPeriodAmount=" + remainingPeriodAmount + ", " : "")
				+ (controlTotal != null ? "controlTotal=" + controlTotal + ", " : "")
				+ (datapass != null ? "datapass=" + datapass + ", " : "")
				+ (litros != null ? "litros=" + litros + ", " : "")
				+ (ctPosition != null ? "ctPosition=" + ctPosition + ", " : "")
				+ (restrL != null ? "restrL=" + restrL + ", " : "") + (restrM != null ? "restrM=" + restrM + ", " : "")
				+ (restrX != null ? "restrX=" + restrX + ", " : "") + (restrJ != null ? "restrJ=" + restrJ + ", " : "")
				+ (restrV != null ? "restrV=" + restrV + ", " : "") + (restrS != null ? "restrS=" + restrS + ", " : "")
				+ (restrD != null ? "restrD=" + restrD : "") + "]";
	}

	/**
	 *  Para extended getVehiclesByRestrictionTypeCard
	 * @param idCard
	 * @param idDepartment
	 * @param nameDepto
	 * @param restrictionType
	 * @param typeBalance
	 * @param idVehicle
	 * @param cardnum
	 * @param remainingAmount
	 * @param plate
	 * @param nickname
	 * @param cardStatus
	 * @param productCode
	 * @param documentType
	 * @param reqCardStatus
	 */
	public MonitorCardDTO(Long idCard, Long idDepartment, String nameDepto, String restrictionType, String typeBalance,
			Long idVehicle, String cardnum, BigDecimal remainingAmount, String plate, String nickname,
			String cardStatus, String productCode, String documentType, String reqCardStatus) {
		super();
		this.idCard = idCard;
		this.idDepartment = idDepartment;
		this.nameDepto = nameDepto;
		this.restrictionType = restrictionType;
		this.typeBalance = typeBalance;
		this.idVehicle = idVehicle;
		this.cardnum = cardnum;
		this.remainingAmount = remainingAmount;
		this.plate = plate;
		this.nickname = nickname;
		this.cardStatus = cardStatus;
		this.productCode = productCode;
		this.documentType = documentType;
		this.reqCardStatus = reqCardStatus;
	}
	
	
	
}
