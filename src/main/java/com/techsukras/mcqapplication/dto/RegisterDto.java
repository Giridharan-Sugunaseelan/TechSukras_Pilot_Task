package com.techsukras.mcqapplication.dto;

import com.techsukras.mcqapplication.entities.Standard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class RegisterDto {

    private Long studentId;

    private String studentName;

    private String email;

    private String password;

    private String standard;


}
