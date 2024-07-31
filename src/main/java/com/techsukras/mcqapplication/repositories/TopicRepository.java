package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findAllBySubject_SubIdAndStandard_StdId(Long subjectId, Long standardId);

}
