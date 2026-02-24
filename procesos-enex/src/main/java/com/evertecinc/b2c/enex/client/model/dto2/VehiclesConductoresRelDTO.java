package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehiclesConductoresRelDTO {
	
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
	
	public VehiclesConductoresRelDTO(Long idConductor, Long idVehicle, String patente, Long idCard, String cardnumber,
			String nombreConductor, String rutConductor, String telefonoConductor, String estadoConductor,
			Long idCliente) {
		super();
		this.idConductor = idConductor;
		this.idVehicle = idVehicle;
		this.patente = patente;
		this.idCard = idCard;
		this.cardnumber = cardnumber;
		this.nombreConductor = nombreConductor;
		this.rutConductor = rutConductor;
		this.telefonoConductor = telefonoConductor;
		this.estadoConductor = estadoConductor;
		this.idCliente = idCliente;
	}

}
