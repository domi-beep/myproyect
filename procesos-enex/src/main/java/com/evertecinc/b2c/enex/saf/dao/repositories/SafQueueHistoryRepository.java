package com.evertecinc.b2c.enex.saf.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.saf.model.entities.SafQueueHistory;

@Repository
public interface SafQueueHistoryRepository extends JpaRepository<SafQueueHistory, Long> {

    List<SafQueueHistory> findByIdQueueOrderByDateInsDesc(Integer idQueue);
}