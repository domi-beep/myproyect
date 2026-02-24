package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.JefeZona;

@Repository
public interface JefeZonaRepository extends JpaRepository<JefeZona, Long> {

    // Método para buscar un JefeZona por su RUT
    Optional<JefeZona> findByRut(String rut);

    // Método para buscar jefes de zona por su estado
    List<JefeZona> findByStatus(String status);
}
