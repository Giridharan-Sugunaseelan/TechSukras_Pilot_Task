package com.techsukras.mcqapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TopicDto {

    private Long topicId;

    private String topicTitle;

    private Set<MCQDto> questions;

    private Long standardId;

    private Long subjectId;

}
