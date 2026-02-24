package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.PreciosLitrosDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListPriceLitersResultDTO extends GenericResultDTO{
	@JsonProperty("listPriceLiters")
	private Optional<List<PreciosLitrosDTO>> listPriceLiters;
}
