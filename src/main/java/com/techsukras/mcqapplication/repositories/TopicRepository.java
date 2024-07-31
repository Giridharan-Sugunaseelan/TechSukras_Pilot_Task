package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
