package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReasignarMontoDeptDTO {

	private Long idDeptoOrigen;
	private BigDecimal nuevoMonto;
	private Long idDeptoDestino;
	private String prodCode;
	private String documentType;
	private String typeBalanceOrigen;
	private BigDecimal orpakMontoOrigen;
	private String typeBalanceDestino;
	private Long idRef;
	
	/**
	 * Descripcion del movimiento motivo de esta actualizacion de saldo
	 */
	private String movimiento;
	/**
	 * Usuario que ejecuto la modificacion de saldo
	 */
	private String username;
}
