package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Standard;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StandardRepository extends JpaRepository<Standard, Long> {

    Optional<Standard> findByStdName(String name);

}
