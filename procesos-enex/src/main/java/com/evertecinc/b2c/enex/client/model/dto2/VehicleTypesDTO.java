package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleTypesDTO {

	private String vehicleTypeCode;
	private String name;
	private String active;

	public VehicleTypesDTO(String vehicleTypeCode, String name, String active) {
		super();
		this.vehicleTypeCode = vehicleTypeCode;
		this.name = name;
		this.active = active;
	}

}