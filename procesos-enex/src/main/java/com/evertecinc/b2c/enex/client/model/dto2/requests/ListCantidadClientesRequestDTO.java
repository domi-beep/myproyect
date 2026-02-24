package com.evertecinc.b2c.enex.client.model.dto2.requests;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListCantidadClientesRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("serialVersionUID")
	private static final long serialVersionUID = -9122598831508471432L;
	
	@JsonProperty("movimiento")
	private String movimiento;
	
	@JsonProperty("rol")
	private Integer rol;
	
	@JsonProperty("idUser")
	private Integer idUser;
	
	@JsonProperty("idCliente")
	private Integer idCliente;
	
	@JsonProperty("fecha")
	private String fecha;
	
	@JsonProperty("limitar")
	private boolean limitar;
	
	@JsonProperty("limite")
	private Integer limite;
	
	@JsonProperty("dateIni")
	private LocalDate dateIni;
	
	@JsonProperty("dateEnd")
	private LocalDate dateEnd;
	
	@JsonProperty("tipoCliente")
	private String tipoCliente;
	
	@JsonProperty("fechaIni")
	private String fechaIni;
	
	@JsonProperty("fechaFin")
	private String fechaFin;
	
	@JsonProperty("plate")
	private String plate;
	
	@JsonProperty("product")
	private Integer product;
	
	@JsonProperty("isCredit")
	private boolean isCredit;
	
	@JsonProperty("actual")
	private boolean actual;
	
	@JsonProperty("producto")
	private String producto;
	
	@JsonProperty("productoCodigo")
	private String productoCodigo;
	
	@JsonProperty("controlPass")
	private Integer controlPass;
	
	@JsonProperty("documentType")
	private String documentType;
	
	@JsonProperty(value="dateEndFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateEndFormatted;
	
	@JsonProperty(value="dateIniFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateIniFormatted = LocalDateTime.now().minusMonths(3);
	
}
