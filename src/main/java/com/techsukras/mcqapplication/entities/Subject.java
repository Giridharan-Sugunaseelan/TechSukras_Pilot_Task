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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    private String subjectName;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Standard.class)
    private Set<Standard> standards = new HashSet<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Topic.class)
    private Set<Topic> topics = new HashSet<>();

}
