package com.techsukras.mcqapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDto {

    private Long studentId;

    private String studentName;

    private StandardDto standard;

}
