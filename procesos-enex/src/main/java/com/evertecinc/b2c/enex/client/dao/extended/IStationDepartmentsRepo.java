package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.AreaByStationsDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.RegionsByStationDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsByCriteriaForDepartmentRequestDTO;

public interface IStationDepartmentsRepo {
	
	public List<StationsDTO> getConstraintsByDepartment(StationClientsRequestDTO params);
	
	public List<RegionDTO> getRegionsByStationsDepartments(RegionsByStationDepartmentRequestDTO params);
	
	public List<AreaDTO> getAreaByStationsDepartments(AreaByStationsDepartmentRequestDTO params);
	
	public List<StationsDTO> getStationsByCriteriaForDepartment(StationsByCriteriaForDepartmentRequestDTO params);

}
