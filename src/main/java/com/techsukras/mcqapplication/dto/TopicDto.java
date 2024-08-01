package com.techsukras.mcqapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private Long topicId;

    private String topicTitle;

    private Set<MCQDto> questions;

    private Long standardId;

    private Long subjectId;

}
