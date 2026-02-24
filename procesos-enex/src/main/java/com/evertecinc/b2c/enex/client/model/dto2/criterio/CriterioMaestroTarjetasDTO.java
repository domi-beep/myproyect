package com.evertecinc.b2c.enex.client.model.dto2.criterio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CriterioMaestroTarjetasDTO {

	private String estado;
	private String preambulo;
	private String mifare;
	private Long idVehicle;
}
