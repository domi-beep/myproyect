package com.evertecinc.b2c.enex.client.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.audits.model.entities.Audits;

public interface AuditsRepo extends JpaRepository<Audits, Long> {

}
