package com.techsukras.mcqapplication.controller;

import com.techsukras.mcqapplication.dto.MCQDto;
import com.techsukras.mcqapplication.service.McqService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
