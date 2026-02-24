package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRegistroImpresionJsonRequestDTO {
	
	private Long idQueue;
    private Long idRegion;
    private Long idArea;
    private String area;
    private String direccion;
    private String data;
    private String tipoSaf;
    private String patente;
	

}
