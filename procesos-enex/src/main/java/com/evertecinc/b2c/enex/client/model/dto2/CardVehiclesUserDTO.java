package com.evertecinc.b2c.enex.client.model.dto2;

import com.evertecinc.b2c.enex.client.model.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardVehiclesUserDTO {

	private User user;
	private CardDTO card;
	private VehicleDTO car;
	
}
