package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.PreciosLitrosHistoryDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListPrecioLitrosHistoryResultDTO extends GenericResultDTO{
	private Optional<List<PreciosLitrosHistoryDTO>> listadoHistory;
}
