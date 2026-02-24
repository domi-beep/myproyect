package com.evertecinc.b2c.enex.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateUsuarioRequestDTO {
	
	@JsonProperty("username")
	private String username;

	@JsonProperty("email")
	private String email;

	@JsonProperty("name")
	private String name;

	@JsonProperty("firstName")
	private String firstName;

}
