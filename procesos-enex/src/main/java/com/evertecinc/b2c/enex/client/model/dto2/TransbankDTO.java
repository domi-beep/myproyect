package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransbankDTO extends PayDTO{
	
	private String tbkAccion;
	private Long tbkCodigoComercio;
	private String tbkCodigoComercioEnc;
	private String tbkTipoTransaccion;
	private Long tbkRespuesta;
	private Long tbkFinalNumeroTarjeta;
	private Long tbkFechaContable;
	private Long tbkFechaTransaccion;
	private Long tbkFechaExpiracion;
	private Long tbkHoraTransaccion;
	private String tbkIdSesion;
	private String tbkTipoPago;
	private Long tbkNumeroCuotas;
	private String tbkVci;
	private String tbkMac;
	private Long tbkIdTransaccion;
	private String valmac;
	private String returnCode;
	private String token;
	private Long idOrder;

}
