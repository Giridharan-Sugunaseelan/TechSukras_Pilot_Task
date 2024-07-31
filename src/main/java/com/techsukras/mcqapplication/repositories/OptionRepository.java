package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByMcqId(Long id);

    Optional<Option> findByOptionIdAndMcqId(Long optionId, Long McqId);

}
