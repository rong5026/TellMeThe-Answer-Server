package com.capstone.answer.domain.report.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private ReportRepository repository;

    @AfterEach
    void aftereach(){
        em.clear();
        em.flush();
    }
}