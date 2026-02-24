package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleReqsDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3590779214170703110L;
	
	private Long idReq;
    private Long idClient;
    private Long idDepartment;
    private String vehicleTypeCode;
    private String productCode;
    private Long idUser;
    private String plate;
    private LocalDateTime dateIns;
    private LocalDateTime dateOut;
    private String status;
    private String reqType;
    private Long idUserAut;
    private String documentType;
    private String name;
    private String address;
    private String zone;	

}
