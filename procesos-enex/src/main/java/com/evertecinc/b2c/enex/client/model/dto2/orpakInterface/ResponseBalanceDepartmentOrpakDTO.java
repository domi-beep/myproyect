package com.evertecinc.b2c.enex.client.model.dto2.orpakInterface;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBalanceDepartmentOrpakDTO {
	
	@SerializedName("Data")
    private List<BalanceDataDTO> data;

    @SerializedName("Total")
    private Long total;

    @SerializedName("AggregateResults")
    private String aggregateResults; // O define el tipo que corresponda

    @SerializedName("Errors")
    private String errors; // O define el tipo que corresponda 	
	
	

}
