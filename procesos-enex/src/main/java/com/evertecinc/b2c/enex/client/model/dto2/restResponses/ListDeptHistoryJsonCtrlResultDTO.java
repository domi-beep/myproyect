package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.DepartmentHistoryDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListDeptHistoryJsonCtrlResultDTO extends GenericResultDTO{
	
	private Optional<List<DepartmentHistoryDTO>> listaDepartmentHistory;
	
	private Optional<Long> countListaDepartmentHistory;
	
	

}
