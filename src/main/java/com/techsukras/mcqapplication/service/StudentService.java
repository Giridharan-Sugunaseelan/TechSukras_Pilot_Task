package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.StudentDto;
import com.techsukras.mcqapplication.entities.Student;
import com.techsukras.mcqapplication.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    public StudentDto addStudent(StudentDto dto){
        Student student = this.modelMapper.map(dto, Student.class);
        Student saved = this.studentRepository.save(student);
        return this.modelMapper.map(saved, StudentDto.class);
    }

}
