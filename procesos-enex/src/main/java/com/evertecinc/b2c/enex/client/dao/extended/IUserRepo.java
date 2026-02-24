package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto.UserDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ConfiguracionDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.BusquedaUserRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListConfiguracionesJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListUserByDeptoJsonRequestDTO;

public interface IUserRepo {
	
	public List<UserDTO> getListadoUsersByClient(BusquedaUserRequestDTO params,Pageable pages);
	public Long countGetListadoUsersByClient(BusquedaUserRequestDTO params,Pageable pages);
	
	public List<UserDTO> getListUserByDepartment(ListUserByDeptoJsonRequestDTO params,Pageable pages);
	public Long countListUserByDepartment(ListUserByDeptoJsonRequestDTO params,Pageable pages);

	public List<UserDTO> getListUsuariosByDeptosAndTipoCliente(BusquedaUserRequestDTO params, Pageable paging);
	public Long getCOUNTListUsuariosByDeptosAndTipoCliente(BusquedaUserRequestDTO params);
	
	public List<ConfiguracionDTO> getListConfiguracionesByCriterio(ListConfiguracionesJsonRequestDTO params, Pageable paging);
	public Long getCOUNTListConfiguracionesByCriterio(ListConfiguracionesJsonRequestDTO params);

}
