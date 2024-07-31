package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
