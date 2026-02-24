package com.evertecinc.b2c.enex.client.model.dto2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StationAreaDTO {

	@JsonProperty("nameStation")
	private String nameStation;

	@JsonProperty("nameArea")
	private String nameArea;

	@JsonProperty("nameRegion")
	private String nameRegion;

	@JsonProperty("nameCity")
	private String nameCity;

	@JsonProperty("codeStation")
	private String codeStation;

	@JsonProperty("codeRegion")
	private String codeRegion;

	@JsonProperty("codeArea")
	private String codeArea;

	@JsonProperty("codeCity")
	private String codeCity;

	@JsonProperty("cantidad")
	private Integer cantidad;

	@JsonProperty("controlTotal")
	private Boolean controlTotal;

	@JsonProperty("latitud")
	private String latitud;

	@JsonProperty("longitud")
	private String longitud;

	@JsonProperty("clientType")
	private String clientType;
	
	@JsonProperty("clientTypes")
	private List<String> clientTypes;

	@JsonProperty("idZone")
	private String codeElectrolinera;

	public StationAreaDTO(String nameStation, String nameArea, String nameRegion, String nameCity, String codeStation,
			String codeRegion, String codeArea, Boolean controlTotal, String latitud, String longitud, String clientType,
			String codeElectrolinera) {
		super();
		this.nameStation = nameStation;
		this.nameArea = nameArea;
		this.nameRegion = nameRegion;
		this.nameCity = nameCity;
		this.codeStation = codeStation;
		this.codeRegion = codeRegion;
		this.codeArea = codeArea;
		this.controlTotal = controlTotal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.clientType = clientType;
		this.codeElectrolinera = codeElectrolinera;
	}
	
	public String toStringNotNulls() {
		return "StationAreaDTO [" + (nameStation != null ? "nameStation=" + nameStation + ", " : "")
				+ (nameArea != null ? "nameArea=" + nameArea + ", " : "")
				+ (nameRegion != null ? "nameRegion=" + nameRegion + ", " : "")
				+ (nameCity != null ? "nameCity=" + nameCity + ", " : "")
				+ (codeStation != null ? "codeStation=" + codeStation + ", " : "")
				+ (codeRegion != null ? "codeRegion=" + codeRegion + ", " : "")
				+ (codeArea != null ? "codeArea=" + codeArea + ", " : "")
				+ (codeCity != null ? "codeCity=" + codeCity + ", " : "")
				+ (cantidad != null ? "cantidad=" + cantidad + ", " : "")
				+ (controlTotal != null ? "controlTotal=" + controlTotal + ", " : "")
				+ (latitud != null ? "latitud=" + latitud + ", " : "")
				+ (longitud != null ? "longitud=" + longitud + ", " : "")
				+ (clientType != null ? "clientType=" + clientType + ", " : "")
				+ (codeElectrolinera != null ? "codeElectrolinera=" + codeElectrolinera : "") + "]";
	}


	/**
	 * Constructor para getStationArea repo extended
	 * @param nameStation
	 * @param nameArea
	 * @param nameRegion
	 * @param nameCity
	 * @param codeStation
	 * @param codeRegion
	 * @param codeArea
	 */
	public StationAreaDTO(String nameStation, String nameArea, String nameRegion, String nameCity, String codeStation,
			String codeRegion, String codeArea, String clientType) {
		super();
		this.nameStation = nameStation;
		this.nameArea = nameArea;
		this.nameRegion = nameRegion;
		this.nameCity = nameCity;
		this.codeStation = codeStation;
		this.codeRegion = codeRegion;
		this.codeArea = codeArea;
		this.clientType = clientType;
	}
	
	

}
