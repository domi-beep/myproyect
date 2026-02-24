package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingDataDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingSortDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GenericResultDTO {

	@JsonProperty("paging_data")
    private Optional<PagingDataDTO> pagingData;

    @JsonProperty("sort")
    private Optional<PagingSortDTO> sort;
    	
    @JsonProperty("observation")
    private Optional<String> observation;
    
//    @JsonProperty("statusCode")
//    private Optional<HttpStatusCode> statusCode;

    @JsonProperty("statusCode")
    private Optional<Integer> statusCode;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenericResultDTO [pagingData=");
		builder.append(pagingData);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", observation=");
		builder.append(observation);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}
