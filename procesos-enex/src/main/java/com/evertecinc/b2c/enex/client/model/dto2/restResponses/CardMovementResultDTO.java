package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.CardMovementDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardMovementResultDTO extends GenericResultDTO {
	
	@JsonProperty("listCardMovement")
	private Optional<List<CardMovementDTO>> listCardMovement;
	
	@JsonProperty("countListCardMovement")
	private Optional<Long> countListCardMovement;
	

}
