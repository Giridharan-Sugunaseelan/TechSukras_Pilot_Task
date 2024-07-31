package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.MCQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface McqRepository extends JpaRepository<MCQ, Long> {

    List<MCQ> findAllByTopicId(Long id);

    Optional<MCQ> findByMcqIdAndTopicId(Long mcqId, Long topicId);

}
