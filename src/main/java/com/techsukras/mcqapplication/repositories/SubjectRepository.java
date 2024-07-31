package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByStandardId(Long standardId);

    Optional<Subject> findByStandardIdAndSubjectId(Long standardId, Long subjectId);
}
