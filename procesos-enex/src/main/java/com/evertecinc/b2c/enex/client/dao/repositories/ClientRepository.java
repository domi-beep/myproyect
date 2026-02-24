package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@EntityGraph(attributePaths = {"addressDisArea", "addressDisCity", "addressDisRegion"})
	Optional<Client> getByIdClient(Long idClient);

    Optional<Client> findByUpi(String upi);

    List<Client> findByClientStatus(String clientStatus);

    Optional<Client> findByContactEmail(String email);

    List<Client> findByAccountJdeClient(String jdeClient);
    
    Optional<List<Client>> findByFactDepartmentOld(Integer factDepartment);
}
