package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class TimeRestiction {
	
	private int day; //dias de la semana ej: lunes:1 martes:2
	private String fromtime; //formato hora 24:59
	private String totime; //formato hora 24:59

}
