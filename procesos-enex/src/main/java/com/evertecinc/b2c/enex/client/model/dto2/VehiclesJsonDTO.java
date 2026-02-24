package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Data;

@Data
public class VehiclesJsonDTO {

	private String plate;
	private String clientName;
	private Long idClient;
	private Long idVehicle;
	private String controlTotal;
	private String datapass;
	private String documentType;
	private String deptoName;
	private String vehicleType;
	private String productCode;
	private String productName;
	private String nickname;
	private Long idCard;

	public VehiclesJsonDTO(String plate, String clientName, Long idClient, Long idVehicle, String controlTotal,
			String datapass, String documentType, String deptoName, String vehicleType, String productCode,
			String productName, String nickname, Long idCard) {
		super();
		this.plate = plate;
		this.clientName = clientName;
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.controlTotal = controlTotal;
		this.datapass = datapass;
		this.documentType = documentType;
		this.deptoName = deptoName;
		this.vehicleType = vehicleType;
		this.productCode = productCode;
		this.productName = productName;
		this.nickname = nickname;
		this.idCard = idCard;
	}

}
