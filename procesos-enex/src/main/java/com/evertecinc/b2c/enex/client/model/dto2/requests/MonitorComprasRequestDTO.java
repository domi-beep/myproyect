package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.math.BigDecimal;
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
public class MonitorComprasRequestDTO extends CriterioBusquedaGenericoDTO{

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("legalName")
	private String legalName;
	
	@JsonProperty("payType")
	private String payType;
	
	@JsonProperty("totalOrder")
	private BigDecimal totalOrder;
	
	@JsonProperty("idOrder")
	private Long idOrder;
	
	@JsonProperty("orderStatus")
	private String orderStatus;
	
	@JsonProperty("numComprobante")
	private String numComprobante;
	
	//filtro fecha
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value="dateIn", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateIn;

//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value="dateEnd", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;
	
	@JsonProperty(value="dateEndFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateEndFormatted;
	
	@JsonProperty(value="dateInFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateInFormatted;
	
	
	
	
	/*
     AND (O.crtdate::date BETWEEN '{dateFirst}'::date AND '{dateEnd}'::date OR '{dateFirst}' IS NULL OR '{dateEnd}' IS NULL)
	*/
}
