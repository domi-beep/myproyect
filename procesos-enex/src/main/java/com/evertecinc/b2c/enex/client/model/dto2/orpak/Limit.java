package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Limit {
	
	private int durationtype;
	private int duration;
	private int valuetype;
	private int value;

}
