package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehicleOdometerDTO {
	
	private String plate;
	
	private BigDecimal odometer;

}
