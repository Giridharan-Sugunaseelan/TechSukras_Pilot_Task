package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.StandardDto;
import com.techsukras.mcqapplication.entities.Standard;
import com.techsukras.mcqapplication.repositories.StandardRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StandardService {

    private StandardRepository standardRepository;

    private ModelMapper modelMapper;

    public StandardDto addStandard(StandardDto dto){
        Standard standard = this.modelMapper.map(dto, Standard.class);
        Standard saved = this.standardRepository.save(standard);
        return this.modelMapper.map(saved, StandardDto.class);
    }


}
