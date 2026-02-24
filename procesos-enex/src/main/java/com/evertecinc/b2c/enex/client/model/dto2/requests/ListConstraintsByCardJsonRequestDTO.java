package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ListConstraintsByCardJsonRequestDTO extends ConstraintsByCardRequestDTO {
	@JsonProperty("idCard")
	private Long idCard;

	@JsonProperty("zone")
	private String zone;

	@JsonProperty("region")
	private String region;

	@JsonProperty("area")
	private String area;

	@JsonProperty("nivel")
	private String nivel;

	@JsonProperty("controlPass")
	private Boolean controlPass;
}
