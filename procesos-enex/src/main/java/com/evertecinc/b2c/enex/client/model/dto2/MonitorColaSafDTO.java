package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorColaSafDTO extends MonitorTipoSafDTO{
	
	private Integer min;
	private Integer max;
	private String estado;

}
