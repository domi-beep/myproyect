package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3590779214170703110L;
	
	private Long    idVehicle;
	private Long    idClient;
	private Long    idDepartment;
	private String  productCode;
	private String  vehicleTypeCode;
	private String  plate;
	private String  documentType;
	private String  vehicleStatus;
	private String  nickname;
	private String  warningStockChannel;
    private BigDecimal warningStock;
    private String warningStockCelular;
    private String warningStockMail;
    private String warningLoadChannel;
    private String warningLoadCelular;
    private String warningLoadMail;
    private String warningLoadFailChannel;
    private String warningLoadFailCelular;
    private String warningLoadFailEmail;
    private List<CardDTO> card;
    private Boolean controlTotal = false;
    private Boolean datapass;
    private Boolean datachip;
    private String nombreDepto;
    private String nombreProducto;
    private String tipoVehiculo;
    private String gps;
	

}
