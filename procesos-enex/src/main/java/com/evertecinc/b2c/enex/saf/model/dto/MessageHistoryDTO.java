package com.evertecinc.b2c.enex.saf.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHistoryDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 27310626064293705L;
	/**
	 * Identificador unico
	 */
	private Long idHistory;
	/**
	 * Identificador queue saf
	 */
	private Long idQueue;
	/**
	 * fecha mensaje
	 */
	private LocalDateTime dateIns;
	/**
	 * Codigo de retorno
	 */
	private String returnCode;
	/**
	 * Mensaje retorno
	 */
	private String message;
	/**
	 * Url enviada
	 */
	private String uri;

}
