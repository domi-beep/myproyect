package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfiguracionDTO {

	private Long idCard;
	private String cardnum;
	private String version;
	private String plate;
	private String cardStatus;
	private String restrictionType;
	private String restrType;
	private Long restrHini;
	private Long restrHend;
	private Long restrDailyMaxQuantity;
	private Long restrDailyMaxLoads;
	private BigDecimal restrAmountMax;
	private Integer eds;
	private Long idDepartment;
	private Long idVehicle;
	private Integer cantidad;
	
	public ConfiguracionDTO(Long idCard,
			String cardnum,
			String version,
			String plate,
			String cardStatus,
			String restrictionType,
			String restrType,
			Long restrHini,
			Long restrHend,
			Long restrDailyMaxQuantity,
			Long restrDailyMaxLoads,
			BigDecimal restrAmountMax,
			Integer eds,
			Long idDepartment,
			Long idVehicle) {
		this.idCard = idCard;
		this.cardnum = cardnum;
		this.version = version;
		this.plate = plate;
		this.cardStatus = cardStatus;
		this.restrictionType = restrictionType;
		this.restrType = restrType;
		this.restrHini = restrHini;
		this.restrHend = restrHend;
		this.restrDailyMaxQuantity = restrDailyMaxQuantity;
		this.restrDailyMaxLoads = restrDailyMaxLoads;
		this.restrAmountMax = restrAmountMax;
		this.eds = eds;
		this.idDepartment = idDepartment;
		this.idVehicle = idVehicle;
	}



}
