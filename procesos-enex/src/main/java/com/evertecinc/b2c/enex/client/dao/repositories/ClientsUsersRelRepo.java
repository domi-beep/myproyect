package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.ClientsUsersRel;
import com.evertecinc.b2c.enex.client.model.entities.ClientsUsersRelId;

public interface ClientsUsersRelRepo extends JpaRepository<ClientsUsersRel, ClientsUsersRelId> {

	public ClientsUsersRel findByClientUpiAndUserRut(String upi, String rut);
	
	public ClientsUsersRel findByClientUpiAndUserRutAndClientClientType(String upi, String rut, String clientType);
	
	public ClientsUsersRel findByUserIdUserAndUserRutAndClientUpi(Long idUser, String Rut, String upi);
	
	public List<ClientsUsersRel> findByUserStatusAndUserStatuskcAndUserRoleInAndUserUsernameNotNullAndUserEmailNotNull(String status,
			String statuskc, List<Long> roles);

}
