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
public class MCQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mcqId;

    private String question;

    @OneToMany(mappedBy = "mcq", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Options.class)
    private Set<Options> options = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Topic.class)
    @JoinColumn(name = "topicId", referencedColumnName = "topicId")
    private Topic topic;

}
