package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.Data;

@Data
public class GetCardsByDepartmentRequestDTO {
	public Long idDepartment;
	public Boolean actDepto;
}