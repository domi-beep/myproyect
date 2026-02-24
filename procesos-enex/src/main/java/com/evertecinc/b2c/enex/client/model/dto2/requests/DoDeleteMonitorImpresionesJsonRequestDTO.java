package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoDeleteMonitorImpresionesJsonRequestDTO {
	
	private String items;
	private String opcion;
	

}
