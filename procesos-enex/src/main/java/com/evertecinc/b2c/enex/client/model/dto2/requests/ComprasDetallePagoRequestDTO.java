package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
public class ComprasDetallePagoRequestDTO extends CriterioBusquedaGenericoDTO{

	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("legalName")
	private String legalName;

	@JsonProperty("payType")
	private String payType;

	@JsonProperty("totalOrder")
	private BigDecimal totalOrder;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "dateIn", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "dateEnd", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateEnd;

	@JsonProperty("idOrder")
	private Long idOrder;

	@JsonProperty("orderStatus")
	private String orderStatus;
	
	@JsonProperty(value="dateEndFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateEndFormatted;
	
	@JsonProperty(value="dateInFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateInFormatted;

}
