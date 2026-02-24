package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdConfigDepartmentJsonRequestDTO extends CriterioBusquedaGenericoSessionDTO {

	@JsonProperty("restrC")
	private Boolean restrC;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("idClient")
	private Long idClient;

	@JsonProperty("restrType")
	private String restrType;

	@JsonProperty("listadoIdCard")
	private String listadoIdCard;

	@JsonProperty("diaLunes")
	private Boolean diaLunes;

	@JsonProperty("diaMartes")
	private Boolean diaMartes;

	@JsonProperty("diaMiercoles")
	private Boolean diaMiercoles;

	@JsonProperty("diaJueves")
	private Boolean diaJueves;

	@JsonProperty("diaViernes")
	private Boolean diaViernes;

	@JsonProperty("diaSabado")
	private Boolean diaSabado;

	@JsonProperty("diaDomingo")
	private Boolean diaDomingo;

	@JsonProperty("restHini")
	private Integer restHini;

	@JsonProperty("restHend")
	private Integer restHend;

	@JsonProperty("restAmountMax")
	private Double restAmountMax;

	@JsonProperty("restDailyMax")
	private Integer restDailyMax;

	@JsonProperty("restDailyMaxQuan")
	private Integer restDailyMaxQuan;

	@JsonProperty("estacion")
	private String estacion;

	@JsonProperty("validacionGPS")
	private String validacionGPS;

}
