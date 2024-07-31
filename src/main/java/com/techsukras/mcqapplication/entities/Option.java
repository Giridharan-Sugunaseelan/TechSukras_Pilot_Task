package com.techsukras.mcqapplication.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Options")
public class Option
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @Column(nullable = false)
    private String optionContent;

    @Column(nullable = false)
    private Boolean isCorrect;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = MCQ.class)
    @JoinColumn(name = "mcqId", referencedColumnName = "mcqId",nullable = false)
    private MCQ mcq;

}
