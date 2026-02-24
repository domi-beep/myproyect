package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorDTO {
	
	private String tipoSaf;
	private String	desc;
	private Integer	cant;
	private Integer	intentos;
	private Integer	min;
	private Integer	max;
	private String estado;
	private LocalDateTime fechaIngreso;
	
	public MonitorDTO(String desc, Integer cant,
			Integer intentos, Integer min, Integer max, String estado) {
		super();
		this.desc = desc;
		this.cant = cant;
		this.intentos = intentos;
		this.min = min;
		this.max = max;
		this.estado = estado;
	}

	public MonitorDTO() {
		// TODO Auto-generated constructor stub
	}

}
