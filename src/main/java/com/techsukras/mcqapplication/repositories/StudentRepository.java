package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

}
