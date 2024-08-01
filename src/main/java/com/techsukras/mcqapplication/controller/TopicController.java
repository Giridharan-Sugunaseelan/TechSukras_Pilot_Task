package com.techsukras.mcqapplication.controller;

import com.techsukras.mcqapplication.dto.TopicDto;
import com.techsukras.mcqapplication.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/topic")
public class TopicController {

    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDto> addTopic(@RequestBody TopicDto dto){
        TopicDto topicDto = this.topicService.addTopic(dto);
        return new ResponseEntity<>(topicDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getTopics(@RequestParam("subId") Long subjectId, @RequestParam("stdId") Long standardId){
        List<TopicDto> topics = this.topicService.getTopicsBySubjectIdAndStandardId(subjectId, standardId);
        return ResponseEntity.ok(topics);
    }

}
