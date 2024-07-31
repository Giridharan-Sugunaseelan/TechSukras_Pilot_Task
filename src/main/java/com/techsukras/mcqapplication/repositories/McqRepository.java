package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.MCQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface McqRepository extends JpaRepository<MCQ, Long> {

    Set<MCQ> findAllByTopic_TopicId(Long id);

    Optional<MCQ> findByMcqIdAndTopic_TopicId(Long mcqId, Long topicId);

}
