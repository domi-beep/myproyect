package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehiclesConductoresRelCriterioDTO {
	
	private Long idConductor;
	private Long idVehicle;
	private String patente;
	private Long idCard;
	private String cardnumber;
	private String nombreConductor;
	private String rutConductor;
	private String telefonoConductor;
	private String estadoConductor;
	private Long idCliente;
	private Long cantidad;
	private String estadoTarjeta;

}
