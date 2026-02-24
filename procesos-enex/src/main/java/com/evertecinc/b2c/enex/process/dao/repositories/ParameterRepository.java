package com.evertecinc.b2c.enex.process.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.process.model.entities.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String> {

}