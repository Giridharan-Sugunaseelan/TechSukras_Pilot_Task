package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.MCQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McqRepository extends JpaRepository<MCQ, Long> {
}
