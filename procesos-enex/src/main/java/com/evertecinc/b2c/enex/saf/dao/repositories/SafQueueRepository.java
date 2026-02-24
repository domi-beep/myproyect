package com.evertecinc.b2c.enex.saf.dao.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;

@Repository
public interface SafQueueRepository extends JpaRepository<SafQueue, Long> {

	List<SafQueue> findByStatus(String status);
    
    List<SafQueue> findByTypeAndStatus(String type, String status);
    
    List<SafQueue> findByDateInsAfter(LocalDateTime date);
    
    long countByStatus(String status);
    
    void deleteByStatus(String status);
    
    List<SafQueue> findByStatusOrderByDateInsAsc(String status);

    @Query("SELECT s FROM SafQueue s WHERE s.status = :status AND s.numRetries > :numRetries")
    List<SafQueue> findByStatusAndNumRetriesGreaterThan(@Param("status") String status, @Param("numRetries") int numRetries);
    
    
    @Query("SELECT s FROM SafQueue s WHERE s.numRetries < 100 " +
            "AND (:#{#dto.idElement} IS NULL OR s.idElement = :#{#dto.idElement}) " +
            "AND (:#{#dto.type} IS NULL OR s.type = :#{#dto.type}) " +
            "AND (:#{#dto.status} IS NULL OR s.status = :#{#dto.status}) " +
            "AND (:#{#dto.numRetries} IS NULL OR s.numRetries = :#{#dto.numRetries}) " +
            "AND (:#{#dto.data} IS NULL OR s.data = :#{#dto.data}) " +
            "AND (:#{#dto.task} IS NULL OR s.task = :#{#dto.task})")
     List<SafQueue> findSafByDto(@Param("dto") SafCriterioDTO dto);
    
    
    @Modifying
    @Query("UPDATE SafQueue q SET q.numRetries = q.numRetries + 1 WHERE q.idQueue = :idQueue")
    void incrementRetriesById(@Param("idQueue") Long idQueue);
    
    long countByTypeAndStatusAndTask(String type, String status, Integer task);

}