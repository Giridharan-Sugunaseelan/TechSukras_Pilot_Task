package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Standard;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRepository extends JpaRepository<Standard, Long> {
}
