package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
