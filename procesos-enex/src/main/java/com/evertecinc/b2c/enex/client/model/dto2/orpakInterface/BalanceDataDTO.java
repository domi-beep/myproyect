package com.evertecinc.b2c.enex.client.model.dto2.orpakInterface;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDataDTO {

	@SerializedName("result")
    private String result;

    @SerializedName("BalanceType")
    private String balanceType;

    @SerializedName("StockType")
    private String stockType;

    @SerializedName("Code")
    private String code;

    @SerializedName("Description")
    private String description;

    @SerializedName("Balance")
    private double balance;
    
    private String returnCode;
    	
}
