package com.techsukras.mcqapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionDto {

    private Long optionId;

    private String optionContent;

    private Boolean isCorrect;

}
