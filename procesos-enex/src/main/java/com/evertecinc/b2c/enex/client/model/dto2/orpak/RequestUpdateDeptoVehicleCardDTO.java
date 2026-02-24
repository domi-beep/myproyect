package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RequestUpdateDeptoVehicleCardDTO {

	private VehicleDTO vehicle;
	private CardDTO card; 
	private DepartmentDTO department; 
	private ClientDTO client;
	
	
	
}
