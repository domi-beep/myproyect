package com.evertecinc.b2c.enex.email.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.email.model.entities.MailQueue;

@Repository
public interface MailQueueRepository extends JpaRepository<MailQueue, Long> {

	 public List<MailQueue> findByStatusAndAttemptsLessThan(String status, int attempts);

}
