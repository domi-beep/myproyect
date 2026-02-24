package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VehicleDTO {
	private Long idVehicle;
	private Long idClient;
	private Long idDepartment;
	private String nameDepartment;
	private String productCode;
	private String vehicleTypeCode;
	private String plate;
	private String documentType;
	private String vehicleStatus;
	private String nickname;
	private String warningStockChannel;
	private BigDecimal warningStock;
	private String warningStockCelular;
	private String warningStockEmail;
	private String warningLoadChannel;
	private String warningLoadCelular;
	private String warningLoadEmail;
	private String warningLoadFailChannel;
	private String warningLoadFailCelular;
	private String warningLoadFailEmail;
	private Integer ct;
	private Integer datapass;
	private Boolean datachip;
	private String gps;
}
