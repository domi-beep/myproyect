package com.evertecinc.b2c.enex.client.model.dto2;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertDTO {
	
	@SerializedName("codigo")
    private String codigo;

    @SerializedName("min")
    private Integer min;

    @SerializedName("max")
    private Integer max;

    @SerializedName("nombre")
    private String nombre;

}
