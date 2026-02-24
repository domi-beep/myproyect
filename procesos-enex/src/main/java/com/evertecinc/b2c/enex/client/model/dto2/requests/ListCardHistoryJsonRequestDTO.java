package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListCardHistoryJsonRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "idUser")
	private Long idUser;
	
	@JsonProperty(value = "idCard")
	private Long idCard;
	
	@JsonProperty(value = "idVehicle")
	private Long idVehicle;
	
	@JsonProperty(value = "username")
	private String username;
	
	@JsonProperty(value = "action")
	private String action;
	
	@JsonProperty(value = "typeBalance")
	private String typeBalance;
	
	@JsonProperty(value = "nameDepartment")
	private String nameDepartment;
	
	@JsonProperty(value = "plate")
	private String plate;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@JsonProperty("dateFirst")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDate dateFirst;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@JsonProperty("dateEnd")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDate dateEnd;
	
	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateFirstLDT;

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateEndLDT;
}
