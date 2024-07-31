package com.techsukras.mcqapplication.dto;


import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Long subId;

    private String subjectName;

    @Override
    public String toString() {
        return "SubjectDto{" +
                "subId=" + subId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }

}
