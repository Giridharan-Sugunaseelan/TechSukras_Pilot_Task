package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.SubjectDto;
import com.techsukras.mcqapplication.entities.Subject;
import com.techsukras.mcqapplication.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubjectService {

    public SubjectRepository subjectRepository;

    public ModelMapper modelMapper;

    public SubjectDto addSubject(SubjectDto dto){
        Subject mapped = this.modelMapper.map(dto, Subject.class);
        System.out.println(mapped);
        Subject saved = this.subjectRepository.save(mapped);
        System.out.println(saved);
        return this.modelMapper.map(saved, SubjectDto.class);
    }

}
