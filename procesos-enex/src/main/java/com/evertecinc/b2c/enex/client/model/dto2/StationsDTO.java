package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StationsDTO {

	private String stationCode;
	private String name;
	private Long idArea;
	private String clientType;
	private Boolean controlPass;
	private String latitud;
	private String longitud;
	private String codeElectrolinera;
	private String areaName;
	private String regionName;
	private String idClient;
	private Short controlTotal;
	private Long idRegion;
	
	/**
	 * Constructor para Repo extended getStationClientsByCriterio
	 */
	public StationsDTO(String stationCode, String name, Long idArea, String clientType, Boolean controlPass,
			String latitud, String longitud, String areaName, String regionName, String idClient) {
		super();
		this.stationCode = stationCode;
		this.name = name;
		this.idArea = idArea;
		this.clientType = clientType;
		this.controlPass = controlPass;
		this.latitud = latitud;
		this.longitud = longitud;
		this.areaName = areaName;
		this.regionName = regionName;
		this.idClient = idClient;
	}

	/**
	 * Constructor para Repo extended getStationsByCriteria
	 */
	public StationsDTO(String stationCode, String name, Long idArea, String clientType, String latitud, String longitud,
			String codeElectrolinera, String areaName, String regionName, Short controlTotal) {
		super();
		this.stationCode = stationCode;
		this.name = name;
		this.idArea = idArea;
		this.clientType = clientType;
		this.controlPass = controlPass;
		this.latitud = latitud;
		this.longitud = longitud;
		this.codeElectrolinera = codeElectrolinera;
		this.areaName = areaName;
		this.regionName = regionName;
		this.controlTotal = controlTotal;
		}

	/**
	 * Constructor para getConstraintsByDepartment
	 */
	public StationsDTO(String stationCode, String name, Long idArea, String areaName, String regionName,
			Long idRegion) {
		super();
		this.stationCode = stationCode;
		this.name = name;
		this.idArea = idArea;
		this.areaName = areaName;
		this.regionName = regionName;
		this.idRegion = idRegion;
	}

	/**
	 *  Constructor getStationsByCriteriaForDepartment
	 */
	public StationsDTO(String stationCode, String name, Long idArea, String clientType, Boolean controlPass,
			String latitud, String longitud) {
		super();
		this.stationCode = stationCode;
		this.name = name;
		this.idArea = idArea;
		this.clientType = clientType;
		this.controlPass = controlPass;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	/**
	 * Se utiliza en el repo extended getStationsByCriteria
	 * @param stationCode
	 * @param name
	 * @param idArea
	 * @param clientType
	 * @param controlPass
	 */
	public StationsDTO(String stationCode, String name, Long idArea, String clientType, Boolean controlPass) {
		super();
		this.stationCode = stationCode;
		this.name = name;
		this.idArea = idArea;
		this.clientType = clientType;
		this.controlPass = controlPass;
	}
	
}
