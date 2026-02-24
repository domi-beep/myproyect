package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetFileDTEJsonCtrlResultDTO extends GenericResultDTO {
	
	@JsonProperty("pdf")
	private byte[] pdf; // Campo para almacenar el PDF
	
	@JsonProperty("httpHeaders")
	private HttpHeaders httpHeaders;
	
	@JsonProperty("httpStatus")
	private HttpStatus httpStatus;

}
