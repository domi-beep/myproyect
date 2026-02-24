package com.evertecinc.b2c.enex.client.model.dto2;

import com.evertecinc.b2c.enex.client.model.entities.JefeZona;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JefeZonaDTO {
	
	private Long idJefeZona;
	private String rut;
	private String name;
	private String phone;
	private String email;
	private String status;
	
	
	public JefeZonaDTO(JefeZona entity) {
		
		this.idJefeZona = entity.getIdJefeZona();
		this.rut = entity.getRut();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.status = entity.getStatus();
	}
	
}
