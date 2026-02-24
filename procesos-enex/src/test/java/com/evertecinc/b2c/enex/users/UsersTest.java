package com.evertecinc.b2c.enex.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.client.dao.repositories.UsersRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UsersTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private @Autowired UsersRepo usersRepoJPA;
	
}
