package com.evertecinc.b2c.enex.email.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.email.model.entities.EmailConfig;

@Repository
public interface EmailConfigRepository extends JpaRepository<EmailConfig, Long> {

	public EmailConfig findByIdEmail(Long idEmail);

}
