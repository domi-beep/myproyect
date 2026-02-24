package com.evertecinc.b2c.enex.audits.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.audits.model.entities.Audits;

@Repository
public interface AuditsRepository extends JpaRepository<Audits, Long> {

}