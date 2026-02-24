package com.evertecinc.b2c.enex.process.jde.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reg1DTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8863889142093522644L;
	
	private String tipoReg;
	private String idVenta;
	private String idCompra;
	private String tipoCompra;
	private String rutCliente;
	private String cuenta;
	private String fechaCompra;
	private String horaCompra;
	private String producto;
	private String total;
	private String departamento;
	private String medioPago;
	private String fechaPago;
	private String horaPago;
	private String banco;
	private String montoTotalProducto;
	private String montoTotalCompra;
	private String codigoAutorizacionPago;
	private String numeroOC;

}
