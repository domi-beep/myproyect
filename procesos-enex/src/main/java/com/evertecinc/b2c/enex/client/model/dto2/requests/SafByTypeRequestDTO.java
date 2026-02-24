package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SafByTypeRequestDTO {
	
	private String type;
	private String idElement;
	private String status;
	private String data;
	private String task; 
	private	Boolean taskisnotnull; 
	private List<String> listaTipos;

}
