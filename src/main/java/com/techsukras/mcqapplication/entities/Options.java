package com.techsukras.mcqapplication.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    private String optionContent;

    private Boolean isCorrect;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = MCQ.class)
    @JoinColumn(name = "mcqId", referencedColumnName = "mcqId",nullable = false)
    private MCQ mcq;

}
