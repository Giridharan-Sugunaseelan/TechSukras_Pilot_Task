package com.techsukras.mcqapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class StandardDto {

    private Long stdId;

    private String stdName;

    private Set<SubjectDto> subjects;

}
