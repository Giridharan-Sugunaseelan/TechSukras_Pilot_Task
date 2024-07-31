package com.techsukras.mcqapplication.security.service;

import com.techsukras.mcqapplication.dto.RegisterDto;
import com.techsukras.mcqapplication.entities.Standard;
import com.techsukras.mcqapplication.entities.Student;
import com.techsukras.mcqapplication.exceptions.StandardNotFoundException;
import com.techsukras.mcqapplication.repositories.StandardRepository;
import com.techsukras.mcqapplication.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private StudentRepository studentRepository;

    private StandardRepository standardRepository;

    private PasswordEncoder passwordEncoder;

    public String register(RegisterDto dto){
        Student student = new Student();
        student.setStudentName(dto.getStudentName());
        student.setEmail(dto.getEmail());
        student.setPassword(passwordEncoder.encode(dto.getPassword()));
        Standard standard = this.standardRepository.findByStdName(dto.getStandard())
                .orElseThrow(() -> new StandardNotFoundException("Standard not found!!!"));
        student.setStandard(standard);
        this.studentRepository.save(student);
        standard.getStudents().add(student);
        this.standardRepository.save(standard);
        return "Student enrolled successfully";
    }
}
