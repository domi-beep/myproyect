package com.evertecinc.b2c.enex.client.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}