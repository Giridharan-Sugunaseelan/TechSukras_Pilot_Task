package com.techsukras.mcqapplication.controller;

import com.techsukras.mcqapplication.dto.SubjectDto;
import com.techsukras.mcqapplication.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto dto){
        System.out.println(dto);
        SubjectDto subjectDto = this.subjectService.addSubject(dto);
        System.out.println(subjectDto);
        return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
    }

}
