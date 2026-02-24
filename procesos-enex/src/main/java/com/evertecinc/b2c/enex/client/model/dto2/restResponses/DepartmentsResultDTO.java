package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepartmentsResultDTO extends GenericResultDTO {
	
	@JsonProperty("listDepartments")
	private Optional<List<DepartmentDTO>> listDepartments;

	@JsonProperty("countListDepartments")
	private Optional<Long> countListDepartments;
	

}
