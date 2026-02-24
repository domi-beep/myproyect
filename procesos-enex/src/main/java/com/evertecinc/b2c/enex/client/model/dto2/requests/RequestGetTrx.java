package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestGetTrx {
	
	@JsonProperty("uniqueID")
	private String uniqueID;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("plate")
	private String plate;

}
