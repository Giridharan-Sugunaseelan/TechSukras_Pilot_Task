package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.SubjectDto;
import com.techsukras.mcqapplication.entities.Standard;
import com.techsukras.mcqapplication.entities.Subject;
import com.techsukras.mcqapplication.exceptions.StandardNotFoundException;
import com.techsukras.mcqapplication.exceptions.StudentNotFoundException;
import com.techsukras.mcqapplication.exceptions.SubjectNotFoundException;
import com.techsukras.mcqapplication.repositories.StandardRepository;
import com.techsukras.mcqapplication.repositories.StudentRepository;
import com.techsukras.mcqapplication.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequestMapping("/subject")
public class SubjectService {

    private SubjectRepository subjectRepository;

    private StandardRepository standardRepository;

    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    public SubjectDto addSubject(SubjectDto dto){
        Subject mapped = this.modelMapper.map(dto, Subject.class);
        System.out.println(mapped);
        Subject saved = this.subjectRepository.save(mapped);
        System.out.println(saved);
        return this.modelMapper.map(saved, SubjectDto.class);
    }

    public List<SubjectDto> getSubjectsByStandardId(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long stdId = this.studentRepository.findByEmail(email).orElseThrow(() -> new StudentNotFoundException("Student not found")).getStandard().getStdId();
        List<Subject> subjects = this.subjectRepository.findAllByStandardId(stdId);
        return subjects.stream().map((subject) -> this.modelMapper.map(subject, SubjectDto.class)).toList();
    }

    public List<String> getSubjectsOfStandard(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long stdId = this.studentRepository.findByEmail(email).orElseThrow(() -> new StudentNotFoundException("Student not found")).getStandard().getStdId();
        return this.subjectRepository.findAllByStandardId(stdId).stream().map(Subject::getSubjectName).toList();
    }

    public SubjectDto addSubjectToStandard(Long subjectId, Long standardId){
        Subject subject = this.subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("Subject not found"));
        Standard standard = this.standardRepository.findById(standardId).orElseThrow(() -> new StandardNotFoundException("Standard not found"));
        standard.getSubjects().add(subject);
        Standard saved = this.standardRepository.save(standard);
        subject.getStandards().add(saved);
        Subject savedSubject = this.subjectRepository.save(subject);
        return this.modelMapper.map(savedSubject, SubjectDto.class);
    }






}
