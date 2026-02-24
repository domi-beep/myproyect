package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListCardsResultDTO extends GenericResultDTO {
	
	@JsonProperty("listTarjetas")
	private Optional<List<MonitorCardDTO>> listCards;

	@JsonProperty("countListTarjetas")
	private Optional<Long> countListCards;
	

}
