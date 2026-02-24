package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBalanceDepartmentDTO {

	private Long    idDepartment;
	private String  productCode;
	private String  documentType;
	private Long    idClient;
    private String  typeBalance;
    private String	idOrpakDepartment;

}
