package com.evertecinc.b2c.enex.client.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	/**
	 * Identificador �nico de la orden
	 */
	private Long idOrder;
	/**
	 * Identificador �nico del cliente
	 */
	private Long idClient;
	/**
	 * Identificador �nico del usuario
	 */
	private Long idUser;
	/**
	 * Monto total de la orden
	 */
	private BigDecimal totalOrder;
	/**
	 * Tipo de pago
	 */
	private String payType;
	/**
	 * Estado Orden
	 * C: Creado
	 * V: En validaci�n
	 * X: En confirmaci�n
	 */
	private String orderStatus;
	/**
	 * Fecha de pago
	 */
	private LocalDateTime payDate;
	/**
	 * Fecha de creacion
	 */
	private LocalDateTime ctrDate;
	
	//Datos del usuario de una orden 
	private UserDTO usuario;
	
	//lista de los productos de una orden
	private List<OrderItemDTO> listOrderItem = new ArrayList<OrderItemDTO>();
	/**
	 * Tipo de la orden
	 * 0: prepago
	 * 1: credito
	 */
	private Integer orderType;

}
