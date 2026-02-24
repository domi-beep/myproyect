package com.evertecinc.b2c.enex.client.model.dto2;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EjecutivoDTO {
	
	private Long idEjecutivo;
	private String rut;
	private String name;
	private String phone;
	private String email;
	private String status;
	
	
	
}
