package com.techsukras.mcqapplication.controller;

import com.techsukras.mcqapplication.dto.SubjectDto;
import com.techsukras.mcqapplication.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<SubjectDto> addSubjectToStandard(@RequestParam("subId") Long subjectId, @RequestParam("stdId") Long standardId){
        SubjectDto subjectDto = this.subjectService.addSubjectToStandard(subjectId, standardId);
        return new ResponseEntity<>(subjectDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<String>> getSubjectTitles(){
        List<String> subjects = this.subjectService.getSubjectsOfStandard();
        return ResponseEntity.ok(subjects);
    }

}
