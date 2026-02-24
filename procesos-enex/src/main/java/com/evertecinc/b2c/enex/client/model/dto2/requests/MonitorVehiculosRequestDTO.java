package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MonitorVehiculosRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("idClient")
	private Long idClient;
	
	@JsonProperty("legalName")
	private String legalName;
	
	@JsonProperty("plate")
	private String plate;
	
	@JsonProperty("nickName")
	private String nickName;
	
	@JsonProperty("vehicleStatus")
	private String vehicleStatus;
	
	@JsonProperty("clientType")
	private String clientType;
	
	@JsonProperty("documentType")
	private String documentType;
	
	@JsonProperty("controlTotal")
	private Boolean controlTotal;
	
	@JsonProperty("codeTypeVehicle")
	private String codeTypeVehicle;
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitorVehiculosRequestDTO [upi=");
		builder.append(upi);
		builder.append(", idClient=");
		builder.append(idClient);
		builder.append(", legalName=");
		builder.append(legalName);
		builder.append(", plate=");
		builder.append(plate);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", vehicleStatus=");
		builder.append(vehicleStatus);
		builder.append(", clientType=");
		builder.append(clientType);
		builder.append(", documentType=");
		builder.append(documentType);
		builder.append(", controlTotal=");
		builder.append(controlTotal);
		builder.append(", codeTypeVehicle=");
		builder.append(codeTypeVehicle);
		builder.append(", idDepartment=");
		builder.append(idDepartment);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	public MonitorVehiculosRequestDTO() {
	}
	
	
	
}
