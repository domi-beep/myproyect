package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.CardUseDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingDataDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingSortDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CargasClientesResultDTO {
	@JsonProperty("listCardUse")
	private List<CardUseDTO> listCardUse;
	
	@JsonProperty("paging_data")
    private PagingDataDTO pagingData;

    @JsonProperty("sort")
    private PagingSortDTO sort;
	

}
