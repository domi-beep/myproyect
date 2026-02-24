package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@ToString
@NoArgsConstructor
public class AreaResultDTO extends GenericResultDTO {
	
	@JsonProperty("listaComunas")
	private Optional<List<AreaDTO>> listaComunas;

	@JsonProperty("countListaComunas")
	private Optional<Long> countListaComunas;

	@Override
	public String toString() {
		return "AreaResultDTO [listaComunas=" + listaComunas + ", countListaComunas=" + countListaComunas
				+ ", getPagingData()=" + getPagingData() + ", getSort()=" + getSort() + ", getObservation()="
				+ getObservation() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}
	
}
