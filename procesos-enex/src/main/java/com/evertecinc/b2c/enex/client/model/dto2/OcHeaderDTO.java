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
public class OcHeaderDTO {

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
	 * Identificador único del jefe de zona
	 */
	private Long idJefeZona;
	/**
	 * Monto total de la orden
	 */
	private BigDecimal total;
	/**
	 * Estado Orden
	 * C: Creado
	 * P: Pendiente
	 * X: Confirmado
	 */
	private String status;
	/**
	 * Fecha de la oc
	 */
	private LocalDateTime date;
	/**
	 * Fecha de creación
	 */
	private LocalDateTime ctrDate;
	/**
	 * Número de la OC
	 */
	private String number;
	
	/** Datos del usuario de una oc */
	private UserDTO usuario;
	
	/** Número de la factura */
	private String invoice;
	
	/** Lista de los productos de una oc */
	private List<OcItemDTO> listOcItem = new ArrayList<OcItemDTO>();

	
}
