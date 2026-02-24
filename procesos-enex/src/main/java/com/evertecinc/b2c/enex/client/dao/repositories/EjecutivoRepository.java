package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Ejecutivo;

@Repository
public interface EjecutivoRepository extends JpaRepository<Ejecutivo, Long> {

    // Consulta personalizada para buscar un Ejecutivo por su RUT
    Optional<Ejecutivo> findByRut(String rut);

    // Consulta para obtener todos los ejecutivos por su estado
    List<Ejecutivo> findByStatus(String status);
}
