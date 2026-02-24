package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleReqsDTO {

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
	private String phone;
	

	public String toStringNotNulls() {
		return "VehicleReqsDTO [" + (idReq != null ? "idReq=" + idReq + ", " : "")
				+ (idClient != null ? "idClient=" + idClient + ", " : "")
				+ (idDepartment != null ? "idDepartment=" + idDepartment + ", " : "")
				+ (vehicleTypeCode != null ? "vehicleTypeCode=" + vehicleTypeCode + ", " : "")
				+ (productCode != null ? "productCode=" + productCode + ", " : "")
				+ (idUser != null ? "idUser=" + idUser + ", " : "") + (plate != null ? "plate=" + plate + ", " : "")
				+ (dateIns != null ? "dateIns=" + dateIns + ", " : "")
				+ (dateOut != null ? "dateOut=" + dateOut + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (reqType != null ? "reqType=" + reqType + ", " : "")
				+ (idUserAut != null ? "idUserAut=" + idUserAut + ", " : "")
				+ (documentType != null ? "documentType=" + documentType + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (address != null ? "address=" + address + ", " : "")
				+ (zone != null ? "zone=" + zone + ", " : "") + (phone != null ? "phone=" + phone : "") + "]";
	}

	
}
