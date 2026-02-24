package com.evertecinc.b2c.enex.client.model.dto2;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MonitorSafHistoryDTO implements Serializable{

	public MonitorSafHistoryDTO(LocalDateTime fecha, String codigoRetorno, String mensaje, String uri,
			Integer cantidad) {
		super();
		this.fecha = fecha;
		this.codigoRetorno = codigoRetorno;
		this.mensaje = mensaje;
		this.uri = uri;
		this.cantidad = cantidad;
	}

	
	private static final long serialVersionUID = 8260120677774565909L;
	
	private LocalDateTime fecha;
	private String codigoRetorno;
	private String mensaje;
	private String uri;
	private Integer cantidad;
	
}
