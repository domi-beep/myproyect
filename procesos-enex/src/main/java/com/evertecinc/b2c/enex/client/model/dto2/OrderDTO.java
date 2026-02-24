package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
	
	/**
	 * Identificador único de la orden
	 */
	private Long idOrder;
	/**
	 * Identificador único del cliente
	 */
	private Long idClient;
	/**
	 * Identificador único del usuario
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
	 * V: En validación
	 * X: En confirmación
	 */
	private String orderStatus;
	/**
	 * Fecha de pago
	 */
	private LocalDateTime payDate;
	/**
	 * Fecha de creación
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
	private Long orderType;

}
