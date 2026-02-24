package com.evertecinc.b2c.enex.email.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertMailDTO {
	
	private String nombre;
	private String mensaje;
	private String channel;
	private String email;
	private String sms;
	private String template;
	private Long   idMailConfig;
	private String tipoCliente;

}
