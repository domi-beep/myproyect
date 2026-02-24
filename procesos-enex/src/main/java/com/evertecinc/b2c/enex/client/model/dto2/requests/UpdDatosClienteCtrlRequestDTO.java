package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdDatosClienteCtrlRequestDTO{
	
	@JsonProperty("idCliente")
	private Long idCliente;
	
	@JsonProperty("contactName")
	private String contactName;
	
	@JsonProperty("contactEmail")
	private String contactEmail;
	
	@JsonProperty("contactEmail2")
	private String contactEmail2;
	
	@JsonProperty("contactPhone")
	private String contactPhone;
	
	@JsonProperty("contactPhone2")
	private String contactPhone2;
	
	@JsonProperty("clientStatus")
	private String clientStatus;
	
	@JsonProperty("txtEESS")
	private String txtEESS;
	
	@JsonProperty("idEjecutivo")
	private Long idEjecutivo;
	
	@JsonProperty("idJefeZona")
	private Long idJefeZona;
	
	private String jwtUsername;
	
	private String jwtEmail;

	@JsonProperty("portal")
	private String portal;
	
	@JsonProperty("warningLockedEmail")
	private String warningLockedEmail;
    
	@JsonProperty("warningStockEmail")
	private String warningStockEmail;
    
	@JsonProperty("warningLockedChannel")
	private String warningLockedChannel;
    
	@JsonProperty("warningStockChannel")
	private String warningStockChannel;
    
	@JsonProperty("warningStock")
	private BigDecimal warningStock;

}
