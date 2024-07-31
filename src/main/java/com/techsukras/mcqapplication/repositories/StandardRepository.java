package com.techsukras.mcqapplication.repositories;

import com.techsukras.mcqapplication.entities.Standard;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface StandardRepository extends JpaAttributeConverter<Standard, Long> {
}
