package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentMovementDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentProductFinderDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentByClientRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListMovimientosDeptoCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ProductDepartmentRequestDTO;

public interface IDepartmentsRepo {

	public List<DepartmentDTO> getDepartmentsByClient(DepartmentByClientRequestDTO params, Pageable pageable);

	public Long getCOUNTDepartmentsByClient(DepartmentByClientRequestDTO params);

	public List<DepartmentDTO> getDepartmentsByIdUser(DepartmentByClientRequestDTO params, Pageable pageable);

	public Long getCOUNTDepartmentsByIdUser(DepartmentByClientRequestDTO params);

	public List<DepartmentDTO> getDeptoHijosRolUser(DepartmentRequestDTO params);

	public Long getCOUNTDeptoHijosRolUser(DepartmentRequestDTO params);
	
	public List<DepartmentMovementDTO> getListDepartmentMovement(ListMovimientosDeptoCtrlRequestDTO params,Pageable pageable);
	
	public Long countListDepartmentMovement(ListMovimientosDeptoCtrlRequestDTO params);
	
	public List<DepartmentProductFinderDTO> getListProductDepartment(ProductDepartmentRequestDTO params, Pageable pages);
	
	public List<DepartmentProductFinderDTO> getProductDepartmentTransf(ProductDepartmentDTO params);
	
	

}
