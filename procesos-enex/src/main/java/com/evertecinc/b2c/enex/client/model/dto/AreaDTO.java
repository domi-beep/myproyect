package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO implements Serializable {

	private static final long serialVersionUID = -1234567890123456789L;

    private Long idArea;
    private Long idRegion;
    private String code;
    private String name;
    private String active;
    private Long idCity;
    private Boolean controlPass;

}
