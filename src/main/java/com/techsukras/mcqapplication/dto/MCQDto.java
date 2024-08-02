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
public class MCQDto {

    private Long mcqId;

    private Integer question_number;

    private String question;

    private String hint;

    private Set<OptionDto> options;

    private Long topicId;

}
