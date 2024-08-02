package com.techsukras.mcqapplication.controller;

import com.techsukras.mcqapplication.dto.MCQDto;
import com.techsukras.mcqapplication.entities.MCQ;
import com.techsukras.mcqapplication.service.McqService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class McqController {

    private McqService mcqService;

    @PostMapping
    public ResponseEntity<MCQDto> addMcq(@RequestBody MCQDto dto){
        MCQDto mcqDto = this.mcqService.addMcqToTopic(dto, dto.getTopicId());
        return new ResponseEntity<>(mcqDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Set<MCQ>> getAllMcq(@PathVariable("id") Long topicId){
        Set<MCQ> questions = this.mcqService.getAllMcqByTopicId(topicId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
