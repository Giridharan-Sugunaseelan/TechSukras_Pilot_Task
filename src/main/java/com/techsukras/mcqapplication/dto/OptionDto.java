package com.techsukras.mcqapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OptionDto {

    private Long optionId;

    private String optionContent;

    private Boolean isCorrect;

}
