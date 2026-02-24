package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoTarjetaRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("idMov")
	private Long idMov;

	@JsonProperty("idCard")
	private Long idCard;

	@JsonProperty("idVehicle")
	private Long idVehicle;

	@JsonProperty("idClient")
	private Long idClient;

	@JsonProperty("movement")
	private String movement;

	@JsonProperty("plate")
	private String plate;

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("username")
	private String username;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("nameDepartment")
	private String nameDepartment;

	@JsonProperty("nickname")
	private String nickname;

	@JsonProperty("controlPass")
	private Integer controlPass;

	@JsonProperty("dataPass")
	private Integer dataPass;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("rutDriver")
	private String rutDriver;

	@JsonProperty(value="dateIn", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateIn; // = LocalDate.now().minusMonths(3L);

	@JsonProperty(value="dateEnd", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd; // = LocalDate.now();
	
	@JsonProperty(value="dateEndFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateEndFormatted; // = LocalDateTime.now().minusMonths(3L);
	
	@JsonProperty(value="dateInFormatted", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateInFormatted; // = LocalDateTime.now();

}