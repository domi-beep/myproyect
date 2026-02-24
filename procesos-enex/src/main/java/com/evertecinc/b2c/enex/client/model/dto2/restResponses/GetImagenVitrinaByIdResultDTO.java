package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ImagenDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetImagenVitrinaByIdResultDTO extends GenericResultDTO{
	
	private Optional<ImagenDTO> imagen;

}
