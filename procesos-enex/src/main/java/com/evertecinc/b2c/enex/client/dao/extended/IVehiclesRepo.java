package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorVehiclesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.VehiclesJsonDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListVehiclesJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorVehiculosRequestDTO;

public interface IVehiclesRepo {
	
//	public List<MonitorVehiculosDTO> getListPatentesClientes(MonitorVehiculosDTO criterio, Pageable pageable);
//	public Long countListPatentesClientes(MonitorVehiculosDTO criterio);
	
	public List<MonitorVehiclesDTO> getListPatentesClientes(MonitorVehiculosRequestDTO params, Pageable paging);

	public Long getCountListPatentesClientes(MonitorVehiculosRequestDTO params, Pageable paging);
	
	public List<MonitorCardDTO> getVehiclesByRestrictionTypeCard(Long idDepartment, Pageable paging);

	public List<VehiclesJsonDTO> getListVehiclesJson(ListVehiclesJsonRequestDTO params, Pageable pageable);

	public Long getCOUNTListVehiclesJson(ListVehiclesJsonRequestDTO params);
	
	
}
