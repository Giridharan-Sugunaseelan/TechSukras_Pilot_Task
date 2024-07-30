package com.techsukras.mcqapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    private String topicTitle;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = MCQ.class)
    private Set<MCQ> questions = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Subject.class)
    @JoinColumn(name = "subjectId", referencedColumnName = "subId" ,nullable = false)
    private Subject subject;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Standard.class)
    @JoinColumn(name = "standardId", referencedColumnName = "stdId", nullable = false)
    private Standard standard;

}
