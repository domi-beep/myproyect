package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoUpdateCardJsonRequestDTO {
	
	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("rutUsuario")
	private String rutUsuario;
	
	@JsonProperty("clientType")
	private String clientType;
	
	@JsonProperty("portal")
	private String portal;
	
	@JsonProperty("idCard")
	private Long idCard; 
	
	@JsonProperty("restrAmountMax")
	private BigDecimal restrAmountMax; 
	
	@JsonProperty("restrDailyMaxLoads")
	private Integer restrDailyMaxLoads; 
	
	@JsonProperty("restrDailyMaxQuantity")
	private Integer restrDailyMaxQuantity; 
	
	@JsonProperty("restrictionType")
	private String restrictionType; 
	
	@JsonProperty("restrType")
	private String restrType; 
	
	@JsonProperty("restrL")
	private Integer restrL; 
	
	@JsonProperty("restrM")
	private Integer restrM; 
	
	@JsonProperty("restrX")
	private Integer restrX; 
	
	@JsonProperty("restrJ")
	private Integer restrJ; 
	
	@JsonProperty("restrV")
	private Integer restrV; 
	
	@JsonProperty("restrS")
	private Integer restrS; 
	
	@JsonProperty("restrD")
	private Integer restrD; 
	
	@JsonProperty("restrHini")
	private Integer restrHini;
	
	@JsonProperty("restrHend")
	private Integer restrHend;
	
	@JsonProperty("estacion")
	private String estacion; 
	
	@JsonProperty("validacionGPS")
	private String validacionGPS; 

}
