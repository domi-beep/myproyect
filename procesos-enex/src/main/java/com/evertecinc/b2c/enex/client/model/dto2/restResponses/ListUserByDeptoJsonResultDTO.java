package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListUserByDeptoJsonResultDTO extends GenericResultDTO{
	
	@JsonProperty("listaUsuarios")
	private Optional<List<UserDTO>> listaUsuarios;
	
	@JsonProperty("countListaUsuarios")
	private Optional<Long> countListaUsuarios;

}
