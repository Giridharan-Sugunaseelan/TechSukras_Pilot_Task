package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.SubjectDto;
import com.techsukras.mcqapplication.entities.Subject;
import com.techsukras.mcqapplication.repositories.StandardRepository;
import com.techsukras.mcqapplication.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    public SubjectRepository subjectRepository;

    public StandardRepository standardRepository;

    public ModelMapper modelMapper;

    public SubjectDto addSubject(SubjectDto dto){
        Subject mapped = this.modelMapper.map(dto, Subject.class);
        System.out.println(mapped);
        Subject saved = this.subjectRepository.save(mapped);
        System.out.println(saved);
        return this.modelMapper.map(saved, SubjectDto.class);
    }

    public List<SubjectDto> getSubjectsByStandardId(Long standardId){
        List<Subject> subjects = this.subjectRepository.findAllByStandardId(standardId);
        return subjects.stream().map((subject) -> this.modelMapper.map(subject, SubjectDto.class)).toList();
    }






}
