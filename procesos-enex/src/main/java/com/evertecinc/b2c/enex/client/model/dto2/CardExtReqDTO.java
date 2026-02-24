package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardExtReqDTO {
	
	private Long idCardExt;
	private String plate;
	private String cardnum;
	private String rut;
	private String nombre;
	private String razonSocial;
	private String tipoProducto;
	private String cuentaD;
	private String file;
	private String tipoTarjeta;
	private Long idUsuario;
	private String estado;
	private String xml;
	private LocalDateTime fechaIns;

}
