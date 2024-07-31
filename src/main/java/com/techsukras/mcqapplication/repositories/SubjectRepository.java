package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s JOIN s.standards std WHERE std.stdId = :standardId")
    List<Subject> findAllByStandardId(@Param("standardId") Long standardId);

    @Query("SELECT s FROM Subject s JOIN s.standards std WHERE std.stdId = :standardId AND s.subId = :subjectId")
    Optional<Subject> findByStandardIdAndSubjectId(@Param("standardId") Long standardId, @Param("subjectId") Long subjectId);
}
