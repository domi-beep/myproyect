package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.VitrinaDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetVitrinasResultDTO extends GenericResultDTO{
	
	private Optional<List<VitrinaDTO>> listaVitrinas;
	
	private Optional<Long> count;
	
	private Optional<String> codigoPosiciones;
	
	private Optional<String> portales;

	

}
