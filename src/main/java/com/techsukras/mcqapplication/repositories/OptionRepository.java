package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
