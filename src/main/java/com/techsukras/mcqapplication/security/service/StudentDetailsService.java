package com.techsukras.mcqapplication.security.service;

import com.techsukras.mcqapplication.entities.Student;
import com.techsukras.mcqapplication.exceptions.StudentNotFoundException;
import com.techsukras.mcqapplication.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentDetailsService implements UserDetailsService {

    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = this.studentRepository.findByEmail(username).orElseThrow(() -> new StudentNotFoundException("Student not found!!!"));

        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .build();
    }
}
