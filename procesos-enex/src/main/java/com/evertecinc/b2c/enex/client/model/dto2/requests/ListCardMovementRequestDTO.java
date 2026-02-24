package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDate;
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
public class ListCardMovementRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("codeStation") 
    private Integer codeStation;

    @JsonProperty("upi") 
    private String upi;

    @JsonProperty("plate") 
    private String plate;

    @JsonProperty("tarjeta") 
    private String tarjeta;

    @JsonProperty("rutChofer") 
    private String rutChofer;

    @JsonProperty("tipoCliente") 
    private String tipoCliente;

    @JsonProperty("razonSocial") 
    private String razonSocial;

    @JsonProperty("tipoCarga") 
    private Integer tipoCarga;

    @JsonProperty("fallidas") 
    private String fallidas;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "dateIn", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDate dateIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "dateEnd", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDate dateEnd;
	
	@JsonProperty(value="dateEndFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateEndFormatted;
	
	@JsonProperty(value="dateInFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateInFormatted = LocalDateTime.now().minusMonths(3);
	
}
