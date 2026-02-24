package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubirArchivoResultDTO extends GenericResultDTO{
	
	private Optional<String> varSession;
	private Optional<Long> idFileAgregado;
	

}
